package kr.co.wikibook.greengram.application.feed.model;

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
