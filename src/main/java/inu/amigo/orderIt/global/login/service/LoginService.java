//package inu.amigo.orderIt.global.login.service;
//
//import inu.amigo.orderIt.domain.user.repository.UserRepository;
//import inu.amigo.orderIt.domain.user.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public LoginService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole().name()) // "enum이 ROLE_으로 시작해야함"
//                .build();
//    }
//}
