package kr.co.wikibook.greengram.application.feed;

import kr.co.wikibook.greengram.application.feed.model.FeedGetDto;
import kr.co.wikibook.greengram.application.feed.model.FeedGetRes;
import kr.co.wikibook.greengram.application.feed.model.FeedPostReq;
import kr.co.wikibook.greengram.config.util.ImgUploadManager;
import kr.co.wikibook.greengram.entity.Feed;
import kr.co.wikibook.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class FeedService {
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;
    private final FeedMapper feedMapper;

    @Transactional
    public FeedPostRes postFeed(Long singedUserId, FeedPostReq req, List<MultipartFile> pics){
        User wirterUser = new User();
        wirterUser.setUserId(singedUserId);

        Feed feed = Feed.builder()
                .writerUser(wirterUser)
                .location(req.getLocation())
                .contents(req.getContents())
                .build();

        feedRepository.save(feed); // feed객체는 영속성을 갖는다.

        List<String> fileNames = imgUploadManager.saveFeedPics(feed.getFeedId(), pics);

        feed.addFeedPics(fileNames);

        return new FeedPostRes(feed.getFeedId(), fileNames);
    }

    public List<FeedGetRes> getFeedList(FeedGetDto dto){
        List<FeedGetRes> list = feedMapper.findAllLimitedTo(dto);
        // 각 피드에서 사진 가져오기
        for (FeedGetRes feedGetRes : list) {
            feedGetRes.setPics(feedMapper.findAllPicByFeedId(feedGetRes.getFeedId()));
        }
        return list;
    }
}
