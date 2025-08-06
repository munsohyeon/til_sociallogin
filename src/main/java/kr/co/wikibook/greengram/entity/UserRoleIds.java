package kr.co.wikibook.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import kr.co.wikibook.greengram.config.enumcode.EnumUserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class UserRoleIds implements Serializable {
    private Long userId;
    @Column(length = 2)
    private EnumUserRole roleCode;
}
