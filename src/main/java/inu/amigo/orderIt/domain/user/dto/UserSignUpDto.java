package inu.amigo.orderIt.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {
    private String username; // uid
    private String password;
    private String email;
}
