package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.user.Member;
import inu.amigo.orderIt.domain.user.Role;
import inu.amigo.orderIt.dto.MemberDto;
import inu.amigo.orderIt.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MemberService {

    MemberRepository memberRepository;
    BCryptPasswordEncoder passwordEncoder;


    public void registerMember(MemberDto memberDto) {
        // 중복 사용자 확인
        if (memberRepository.findByUsername(memberDto.getUsername()) != null) {
            log.info("Username is already taken");
            throw new RuntimeException("Username is already taken");
        }
        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        // Member 엔티티 생성 및 저장
        Member newMember = new Member();
        newMember.setUsername(memberDto.getUsername());
        newMember.setPassword(encodedPassword);
        newMember.setEmail(memberDto.getEmail());

        // 기본 역할 설정 (USER)
        newMember.setRole(Role.OWNER);

        // 생성 및 수정일자 설정
        LocalDateTime now = LocalDateTime.now();
        newMember.setCreatedAt(now);
        newMember.setModifiedAt(now);

        memberRepository.save(newMember);
        log.info("new member is saved");
    }
}