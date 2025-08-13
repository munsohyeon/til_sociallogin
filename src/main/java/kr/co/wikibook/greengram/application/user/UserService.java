package kr.co.wikibook.greengram.application.user;

import kr.co.wikibook.greengram.application.user.model.*;
import kr.co.wikibook.greengram.config.enumcode.model.EnumUserRole;
import kr.co.wikibook.greengram.config.model.JwtUser;
import kr.co.wikibook.greengram.config.util.ImgUploadManager;
import kr.co.wikibook.greengram.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImgUploadManager imgUploadManager;

    @Transactional
    public void signUp(UserSignUpReq req, MultipartFile pic) {
        String hashedPassword = passwordEncoder.encode(req.getUpw());

        User user = new User();
        user.setNickName(req.getNickName());
        user.setUid(req.getUid());
        user.setUpw(hashedPassword);
        user.addUserRole(req.getRoles());

       userRepository.save(user);

        if(pic != null) {
            String savedFileName = imgUploadManager.saveProfilePic(user.getUserId(), pic);
            user.setPic(savedFileName);

            //throw new RuntimeException();
        }
    }

    public UserSignInDto signIn(UserSignInReq req) {
        User user = userRepository.findByUid(req.getUid());
        if(user == null || !passwordEncoder.matches(req.getUpw(), user.getUpw())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디/비밀번호를 확인해 주세요.");
        }
        // User 튜플을 가져왔는데 user_role에 저장되어있는 데이터까지 가져올 수 있었던건 양방향 관계 설정을 했기 때문에 가능
        List<EnumUserRole> roles = user.getUserRoles().stream().map(item -> item.getUserRoleIds().getRoleCode()).toList();
        log.info("role: {}", roles);
        JwtUser jwtUser = new JwtUser(user.getUserId(), roles);

        UserSignInRes userSignInRes = UserSignInRes.builder()
                                                    .userId(user.getUserId())
                                                    .nickName(user.getNickName())
                                                    .pic(user.getPic())
                                                    .build();

        return UserSignInDto.builder()
                            .jwtUser(jwtUser)
                            .userSignInRes(userSignInRes)
                            .build();
    }

    public UserProfileGetRes getProfileUser(UserProfileGetDto req) {
        // 로그인후 유저정보 확인 해달라
        return null;
    }
}