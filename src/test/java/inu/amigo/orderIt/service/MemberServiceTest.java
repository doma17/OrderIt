package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.user.Member;
import inu.amigo.orderIt.domain.user.Role;
import inu.amigo.orderIt.dto.MemberDto;
import inu.amigo.orderIt.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // Given
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("newUser");
        memberDto.setPassword("password");
        memberDto.setEmail("newuser@example.com");

        // When
        memberService.register(memberDto);
        when(memberRepository.findByUsername("newUser")).thenReturn(Optional.of(new Member()));
        Member member = memberService.getMemberByUsername(memberDto.getUsername());

        // Then
         assertThat(memberDto.getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    void 회원가입_유저이름_중복확인() {
        // Given
//        Member member = new Member();
//        member.setUsername("existingUser");
//        member.setPassword("password");
//        member.setEmail("existinguser@example.com");
//        memberService.register(memberDto);

        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("existingUser");
        memberDto.setPassword("password");
        memberDto.setEmail("existinguser@example.com");
        memberService.register(memberDto);

        // When/Then
        assertThrows(IllegalStateException.class, () -> memberService.register(memberDto));
    }

    @Test
    void 멤버DtoMapper() {
        // Given
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("newUser");
        memberDto.setPassword("password");
        memberDto.setEmail("newuser@example.com");

        // When
        Member member = MemberService.convertDtoToMember(memberDto);

        // Then
        assertThat(memberDto.getUsername()).isEqualTo(member.getUsername());
        assertThat(memberDto.getPassword()).isEqualTo(member.getPassword());
        assertThat(memberDto.getEmail()).isEqualTo(member.getEmail());
    }
}
