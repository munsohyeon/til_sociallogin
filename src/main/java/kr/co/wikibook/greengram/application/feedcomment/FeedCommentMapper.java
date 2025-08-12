package kr.co.wikibook.greengram.application.feedcomment;

import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetReq;
import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    List<FeedCommentItem> findAllByFeedIdLimitedTo(FeedCommentGetReq req);
}
