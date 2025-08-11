package kr.co.wikibook.greengram.application.feed;

import kr.co.wikibook.greengram.entity.Feed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedPostRes {
    private long feedId;
    private List<String> pics;
}
