package com.hhplus.week02.domain.member;

import com.hhplus.week02.domain.common.entity.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 유저명 */
    @Column(nullable = false)
    private String name;
}
