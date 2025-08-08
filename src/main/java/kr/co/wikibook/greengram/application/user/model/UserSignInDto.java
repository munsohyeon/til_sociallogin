package kr.co.wikibook.greengram.application.user.model;

import kr.co.wikibook.greengram.config.model.JwtUser;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInDto {
    private UserSignInRes userSignInRes; // 응답용
    private JwtUser jwtUser; // JWT 발행 때 사용
}
