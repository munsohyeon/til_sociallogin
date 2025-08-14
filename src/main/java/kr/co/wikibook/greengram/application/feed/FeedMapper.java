package kr.co.wikibook.greengram.application.feed;

import kr.co.wikibook.greengram.application.feed.model.FeedGetDto;
import kr.co.wikibook.greengram.application.feed.model.FeedGetRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    List<FeedGetRes> findAllLimitedTo(FeedGetDto dto);
    List<String> findAllPicByFeedId(long feedId);
}
