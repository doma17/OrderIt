//package inu.amigo.orderIt.domain.user.controller;
//
//import inu.amigo.orderIt.domain.user.service.UserService;
//import inu.amigo.orderIt.domain.user.dto.UserSignUpDto;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Tag(name = "Member API")
//@Slf4j
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    /**
//     * 회원 가입
//     */
//    @PostMapping("/sign-up")
//    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
//        log.info("[signUp] UserSignUpDto : {}", userSignUpDto.toString());
//        userService.signUp(userSignUpDto);
//        return "회원가입 성공";
//    }
//
//    @GetMapping("/jwt-test")
//    public String jwtTest() {
//        return "jwtTest 요청 성공";
//    }
//}