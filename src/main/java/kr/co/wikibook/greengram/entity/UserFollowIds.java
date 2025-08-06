package kr.co.wikibook.greengram.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserFollowIds {
    private Long fromUserId;
    private Long toUserId;
}
