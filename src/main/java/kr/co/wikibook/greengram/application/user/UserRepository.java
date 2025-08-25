package kr.co.wikibook.greengram.application.user;

import kr.co.wikibook.greengram.config.security.SignInProviderType;
import kr.co.wikibook.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
// <연결할 엔티티, 타입>
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
    User findByUidAndProviderType(String uid, SignInProviderType signInProviderType);
}
