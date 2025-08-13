package kr.co.wikibook.greengram.application.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileGetDto {
    private Long signedUserId;
    private Long profileId;
}
