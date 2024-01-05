package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.user.Member;
import inu.amigo.orderIt.dto.MemberDto;
import inu.amigo.orderIt.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void registerMember_Success() {
        // Given
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("testUser");
        memberDto.setPassword("testPassword");
        memberDto.setEmail("test@example.com");

        when(memberRepository.findByUsername("testUser")).thenReturn(null);
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedTestPassword");
        when(memberRepository.save(any(Member.class))).thenReturn(new Member());

        // When, Then
        assertDoesNotThrow(() -> memberService.register(memberDto));
    }

    @Test
    void registerMember_Fail_DuplicateUsername() {
        // Given
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("existingUser");
        memberDto.setPassword("testPassword");
        memberDto.setEmail("test@example.com");

        when(memberRepository.findByUsername("existingUser")).thenReturn(new Member());

        // When, Then
        assertThrows(IllegalStateException.class, () -> memberService.register(memberDto));
    }
}
