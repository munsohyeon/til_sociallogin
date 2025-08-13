package kr.co.wikibook.greengram.application.feedcomment;

import jakarta.validation.Valid;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetReq;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetRes;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentPostReq;
import kr.co.wikibook.greengram.entity.Feed;
import kr.co.wikibook.greengram.entity.FeedComment;
import kr.co.wikibook.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;

    public Long postFeedComment(long signedUserId, @Valid FeedCommentPostReq req) {
        User user = new User();
        user.setUserId(signedUserId);

        Feed feed = Feed.builder()
                .feedId(req.getFeedId())
                .build();

       FeedComment feedComment = FeedComment.builder()
                                            .feed(feed)
                                            .user(user)
                                            .comment(req.getComment())
                                            .build();

       feedCommentRepository.save(feedComment);
       return feedComment.getFeedCommentId();
    }

    public FeedCommentGetRes getFeedList(FeedCommentGetReq req) {

        return null;
    }
}