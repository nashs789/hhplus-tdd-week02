package com.hhplus.week02.domain.member;

import com.hhplus.week02.domain.common.entity.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member extends Timestamp {

    @Id
    @GeneratedValue
    private Long id;

    /** 유저명 */
    @Column(nullable = false)
    private String name;
}
