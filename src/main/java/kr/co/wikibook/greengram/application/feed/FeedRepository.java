package kr.co.wikibook.greengram.application.feed;

import kr.co.wikibook.greengram.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, String> {

}
