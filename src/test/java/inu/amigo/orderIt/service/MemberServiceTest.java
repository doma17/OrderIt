package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.user.Member;
import inu.amigo.orderIt.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setUsername("kwak");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertThat(member).isEqualTo(memberRepository.findById(saveId));
    }
}