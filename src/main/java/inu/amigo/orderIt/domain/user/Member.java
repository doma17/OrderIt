package inu.amigo.orderIt.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 멤버 엔티티
 */
@Entity
@Getter @Setter @ToString(exclude = "password") // toString() method will not print out PASSWORD!
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;
    @JsonIgnore
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}
