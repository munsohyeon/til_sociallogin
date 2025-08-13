package kr.co.wikibook.greengram.application.feedcomment;

import jakarta.validation.Valid;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetReq;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetRes;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentItem;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentPostReq;
import kr.co.wikibook.greengram.entity.Feed;
import kr.co.wikibook.greengram.entity.FeedComment;
import kr.co.wikibook.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCommentService {
    private final FeedCommentRepository feedCommentRepository;
    private final FeedCommentMapper feedCommentMapper;

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
        List<FeedCommentItem> commentList = feedCommentMapper.findAllByFeedIdLimitedTo(req);
        boolean moreComment = commentList.size() > req.getSize();
        if (moreComment) { //마지막 댓글 삭제
            commentList.remove(commentList.size() - 1); //마지막 아이템 삭제
        }
        return new FeedCommentGetRes(moreComment, commentList);
    }

    public void deleteFeed(Long signedUserId, Long feedCommentId) {
        FeedComment feedComment = feedCommentRepository.findById(feedCommentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "feed_comment_id를 확인해 주세요."));
        // 본인이 작성한 댓글이 아니라면 exception 터트림 "본인이 작성한 댓글이 아닙니다."
        if (signedUserId != feedComment.getUser().getUserId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 작성한 댓글이 아닙니다.");
        }
    }
}