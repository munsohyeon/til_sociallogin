package kr.co.wikibook.greengram.application.follow;

import kr.co.wikibook.greengram.entity.UserFollow;
import kr.co.wikibook.greengram.entity.UserFollowIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<UserFollow, UserFollowIds> {
}
