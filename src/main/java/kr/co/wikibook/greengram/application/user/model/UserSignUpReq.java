package kr.co.wikibook.greengram.application.user.model;

import jakarta.validation.constraints.Pattern;
import kr.co.wikibook.greengram.config.enumcode.model.EnumUserRole;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserSignUpReq {
    // @Size(max = 50, min = 4, message = "아이디는 4~50자까지 작성할 수 있습니다.")
    @Pattern(regexp = "^[A-Za-z0-9]{4,50}$", message = "아이디는 영어, 숫자, 언더바로만 4~50사이로 사용가능합니다.")
    private String uid;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{10,}$", message = "비밀번호 영문자, 숫자, 특수기호로 구성되며 10자 이상이어야 합니다.")
    private String upw;
    @Pattern(regexp = "^[가-힣]{2,10}", message = "닉네임은 한글로 2~10까지 가능합니다.")
    private String nickName;

    private List<EnumUserRole> roles;
}
