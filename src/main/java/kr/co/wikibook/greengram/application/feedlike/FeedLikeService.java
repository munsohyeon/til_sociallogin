package kr.co.wikibook.greengram.application.feedlike;

import jakarta.validation.Valid;
import kr.co.wikibook.greengram.application.feedlike.model.FeedLikeToggleReq;
import kr.co.wikibook.greengram.entity.Feed;
import kr.co.wikibook.greengram.entity.FeedLike;
import kr.co.wikibook.greengram.entity.FeedLikeIds;
import kr.co.wikibook.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedLikeService {
    private final FeedLikeRepository feedLikeRepository;

    public boolean toggle(long signedUserId, FeedLikeToggleReq req) {
        FeedLikeIds feedLikeIds = FeedLikeIds.builder()
                                            .feedId(req.getFeedId())
                                            .userId(signedUserId)
                                            .build();

        FeedLike feedLike = feedLikeRepository.findById(feedLikeIds).orElse(null);

        // 에러를 터트리겠다 후하하하
        if (feedLike == null) { // 좋아요가 아닌 상태
            Feed feed = Feed.builder()
                            .feedId(req.getFeedId())
                            .build();

            User user = new User();
            user.setUserId(signedUserId);

            FeedLike feedLikeSave = FeedLike.builder()
                                            .id(feedLikeIds)
                                            .user(user)
                                            .feed(feed)
                                            .build();

            feedLikeRepository.save(feedLikeSave);
            return true;

        }  // 좋아요인 상태
        feedLikeRepository.delete(feedLike);
        return false;
    }
}
