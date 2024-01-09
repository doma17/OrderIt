//package inu.amigo.orderIt.domain.user.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import lombok.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDateTime;
//
///**
// * 멤버 엔티티
// */
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Getter
//@ToString(exclude = "password") // toString() method will not print out PASSWORD!
//public class User{
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "USER_ID")
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String username; // uid
//
//    @Column(nullable = false)
//    private String password;
//
//    @Email
//    private String email;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    private String refreshToken; // 리프레시 토큰
//
//    @Column(columnDefinition = "TIMESTAMP")
//    private LocalDateTime createdAt;
//
//    // 유저 권한 설정 메소드
//    public void authorizeUser() {
//        this.role = Role.USER;
//    }
//
//    // 비밀번호 암호화 메소드
//    public void passwordEncode(PasswordEncoder passwordEncoder) {
//        this.password = passwordEncoder.encode(this.password);
//    }
//
//    public void updateRefreshToken(String updateRefreshToken) {
//        this.refreshToken = updateRefreshToken;
//    }
//
//}
