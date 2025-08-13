package kr.co.wikibook.greengram.application.feedcomment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class FeedCommentGetReq {
    @Positive
    @NotNull(message = "값을 입력해주세요.")
    private Long feedId;

    @PositiveOrZero
    @NotNull(message = "start_idx는 필수값")
    private Integer startIdx;

    @PositiveOrZero
    @NotNull(message = "size는 필수값")
    private Integer size;

    @ConstructorProperties({"feed_id", "start_idx", "size"})
    // @BindParam("feed_id"), @BindParam("start_idx")
    public FeedCommentGetReq( long feedId, Integer startIdx, Integer size) {
        this.feedId = feedId;
        this.startIdx = startIdx;
        this.size = size;
    }

}
