package inu.amigo.orderIt.user;

import inu.amigo.orderIt.user.entity.User;
import inu.amigo.orderIt.user.entity.Role;
import inu.amigo.orderIt.user.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByUsername(userSignUpDto.getUsername()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        User user = User.builder()
                .username(userSignUpDto.getUsername())
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }
}