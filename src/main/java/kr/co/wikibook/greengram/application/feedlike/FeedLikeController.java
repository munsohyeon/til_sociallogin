package kr.co.wikibook.greengram.application.feedlike;

import jakarta.validation.Valid;
import kr.co.wikibook.greengram.application.feedlike.model.FeedLikeToggleReq;
import kr.co.wikibook.greengram.config.model.ResultResponse;
import kr.co.wikibook.greengram.config.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed/like")
public class FeedLikeController {
    private final FeedLikeService feedLikeService;

    @PostMapping
    public ResultResponse<?> toggle(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                    @Valid @RequestBody FeedLikeToggleReq req) {
        log.info("signedUserId: {}", userPrincipal.getSignedUserId());
        log.info("req={}", req);
         boolean result = feedLikeService.toggle(userPrincipal.getSignedUserId(), req);
        return new ResultResponse<>(result ? "좋아요 처리" : "좋아요  취소", result); // true: 좋아요 처리, false: 좋아요 취소
    }
}
