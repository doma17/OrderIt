package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.user.Member;
import inu.amigo.orderIt.domain.user.Role;
import inu.amigo.orderIt.dto.MemberDto;
import inu.amigo.orderIt.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    // Spring Security
//    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public void register(MemberDto memberDto) {
        // 중복 사용자 확인
        if (memberRepository.findByUsername(memberDto.getUsername()) != null) {
            log.debug("유저 중복");
            throw new IllegalStateException("Username is already taken");
        }
        // 패스워드 암호화

        // Member 엔티티 생성 및 저장
        Member newMember = new Member();
        newMember.setUsername(memberDto.getUsername());
        newMember.setPassword(newMember.getPassword());
        newMember.setEmail(memberDto.getEmail());

        // 기본 역할 설정 (USER)
        newMember.setRole(Role.USER);

        // 생성 및 수정일자 설정
        LocalDateTime now = LocalDateTime.now();
        newMember.setCreatedAt(now);

        memberRepository.save(newMember);
        log.info("새로운 멤버 저장");
    }
}