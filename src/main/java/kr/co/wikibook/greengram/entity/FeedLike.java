package kr.co.wikibook.greengram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FeedLike extends CreatedAt {
    @EmbeddedId
    private FeedLikeIds id;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    @MapsId("feedId")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;

}
