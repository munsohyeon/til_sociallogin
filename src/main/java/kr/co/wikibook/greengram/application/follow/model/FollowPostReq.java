package kr.co.wikibook.greengram.application.follow.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FollowPostReq {
    @Positive
    @NotNull(message = "toUserId는 필수값")
    private Long toUserId;
}
