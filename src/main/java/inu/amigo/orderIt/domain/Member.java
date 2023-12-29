package inu.amigo.orderIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inu.amigo.orderIt.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 멤버 엔티티
 */
@Entity
@Getter
@Setter
@ToString(exclude = "password") // toString() method will not print out PASSWORD!
public class Member {

    /**
     * 사용자 번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 유저 아이디
     */
    private String username;

    /**
     * 유저 비밀번호 (JsonIgnore)
     */
    @JsonIgnore
    private String password;

    /**
     * 유저 이메일
     */
    private String email;

    /**
     * 유저 역할
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * 유저 생성 날짜
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /**
     * 유저 수정 날짜
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;
}
