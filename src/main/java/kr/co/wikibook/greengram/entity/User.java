package kr.co.wikibook.greengram.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table (
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"uid"}
                )
        }
)
public class User extends UpdatedAt {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long user_id; // pk는 보통 long type 권장

    @Column(nullable = false, length = 30)
    private String nick_name;

    @Column(nullable = false, length = 50)
    private String uid;

    @Column(nullable = false, length = 100)
    private String pic;

    @Column(nullable = false, length = 100)
    private String upw;
}
