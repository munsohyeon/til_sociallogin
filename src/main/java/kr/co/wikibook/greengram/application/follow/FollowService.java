package kr.co.wikibook.greengram.application.follow;

import kr.co.wikibook.greengram.application.follow.model.FollowPostReq;
import kr.co.wikibook.greengram.config.model.ResultResponse;
import kr.co.wikibook.greengram.entity.User;
import kr.co.wikibook.greengram.entity.UserFollow;
import kr.co.wikibook.greengram.entity.UserFollowIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public void postUserFollow( long fromUserId, long toUserId ) {
        UserFollowIds  userFollowIds = new UserFollowIds(fromUserId, toUserId);
        log.info("toUserId : {}", toUserId);
        log.info("fromUserId : {}", fromUserId);

        User to = new User();
        to.setUserId(toUserId);

        User from = new User();
        from.setUserId(fromUserId);

        UserFollow userFollow = new UserFollow();
        userFollow.setToUser(to);
        userFollow.setFromUser(from);
        userFollow.setUserFollowIds(userFollowIds);

        followRepository.save(userFollow);
    }

}
