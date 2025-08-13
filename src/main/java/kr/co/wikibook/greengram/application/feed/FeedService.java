package kr.co.wikibook.greengram.application.feed;

import kr.co.wikibook.greengram.application.feed.model.FeedGetDto;
import kr.co.wikibook.greengram.application.feed.model.FeedGetRes;
import kr.co.wikibook.greengram.application.feed.model.FeedPostReq;
import kr.co.wikibook.greengram.application.feed.model.FeedPostRes;
import kr.co.wikibook.greengram.application.feedcomment.FeedCommentMapper;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetReq;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetRes;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentItem;
import kr.co.wikibook.greengram.config.constants.ConstComment;
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
public class FeedService {
    private final FeedMapper feedMapper;
    private final FeedCommentMapper feedCommentMapper;
    private final FeedRepository feedRepository;
    private final ImgUploadManager imgUploadManager;
    private final ConstComment constComment;

    @Transactional
    public FeedPostRes postFeed(long signedUserId, FeedPostReq req, List<MultipartFile> pics) {
        User writerUser = new User();
        writerUser.setUserId(signedUserId);

        Feed feed = Feed.builder()
                .writerUser(writerUser)
                .location(req.getLocation())
                .contents(req.getContents())
                .build();

        feedRepository.save(feed); //feed객체는 영속성을 갖는다.

        List<String> fileNames = imgUploadManager.saveFeedPics(feed.getFeedId(), pics);

        feed.addFeedPics(fileNames);

        return new FeedPostRes(feed.getFeedId(), fileNames);
    }

    public List<FeedGetRes> getFeedList(FeedGetDto dto) {
        List<FeedGetRes> list = feedMapper.findAllLimitedTo(dto);

        //각 피드에서 사진 가져오기, 댓글 가져오기(4개만)
        // final int START_IDX = 0;
        // final int NEED_FOR_VIEW_SIZE = 3; // 피드 리스트를 뿌릴 때 실제로 피드당 보여지는 댓글 수
        // final int MORE_COMMENT_COUNT = 4;

        for(FeedGetRes feedGetRes : list) {
            feedGetRes.setPics(feedMapper.findAllPicByFeedId(feedGetRes.getFeedId()));
            //startIdx:0, size: 4
            FeedCommentGetReq req = new FeedCommentGetReq(feedGetRes.getFeedId(), constComment.startIndex, constComment.needForViewCount);
            List<FeedCommentItem> commentList = feedCommentMapper.findAllByFeedIdLimitedTo(req);
            boolean moreComment = commentList.size() > constComment.needForViewCount; // row 수가 4였을 때만 true가 담기고, row수가 0~3인 경우는 false가 담긴다.
            FeedCommentGetRes feedCommentGetRes = new FeedCommentGetRes(moreComment, commentList);
            feedGetRes.setComments(feedCommentGetRes);
            if(moreComment) { //마지막 댓글 삭제
                commentList.remove(commentList.size()-1); // 마지막 아이템 삭제
            }
        }
        return list;
    }

}