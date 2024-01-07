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

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입 서비스
     *
     * @param memberDto 회원가입 정보를 담은 DTO
     * @throws IllegalStateException 중복된 사용자일 경우 발생하는 예외
     */
    public void register(MemberDto memberDto) throws IllegalStateException {
        // 중복 사용자 확인
        String username = memberDto.getUsername();
        if (memberRepository.findByUsername(username).isPresent()) {
            log.error("유저 중복");
            throw new IllegalStateException("Username is already taken");
        }
        // 패스워드 암호화

        // Member 엔티티 생성 및 저장
        Member newMember = convertDtoToMember(memberDto);

        // 기본 역할 설정 (USER)
        newMember.setRole(Role.USER);

        // 생성 및 수정일자 설정
        LocalDateTime now = LocalDateTime.now();
        newMember.setCreatedAt(now);

        memberRepository.save(newMember);
        log.info("새로운 멤버 저장");
    }

    /**
     * MemberDto to Member Mapper
     *
     * @param memberDto MemberDto 객체
     * @return 변환된 Member 엔티티
     */
    protected static Member convertDtoToMember(MemberDto memberDto) {
        Member newMember = new Member();
        newMember.setUsername(memberDto.getUsername());
        newMember.setPassword(memberDto.getPassword());
        newMember.setEmail(memberDto.getEmail());
        return newMember;
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("Member with ID " + memberId + " not found.")
        );
    }

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("Member with Username " + username + " not found.")
        );
    }
}