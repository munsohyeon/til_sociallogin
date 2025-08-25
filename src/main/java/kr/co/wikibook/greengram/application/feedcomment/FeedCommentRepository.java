package kr.co.wikibook.greengram.application.feedcomment;

import kr.co.wikibook.greengram.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
    void deleteByFeedFeedId(long feedId);
}