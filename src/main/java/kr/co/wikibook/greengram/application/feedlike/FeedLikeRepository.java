package kr.co.wikibook.greengram.application.feedlike;

import kr.co.wikibook.greengram.entity.FeedLike;
import kr.co.wikibook.greengram.entity.FeedLikeIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedLikeRepository extends JpaRepository<FeedLike, FeedLikeIds> {
    void deleteByIdFeedId(long feedId);
}
