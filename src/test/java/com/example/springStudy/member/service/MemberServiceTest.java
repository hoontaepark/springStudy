package com.example.springStudy.member.service;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.when;

import com.example.springStudy.member.application.MemberServiceImp;
import com.example.springStudy.member.infrastructure.MemberRepository;
import com.example.springStudy.member.vo.SignUpVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class MemberServiceTest {


    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImp memberServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void singUp_Success() {
        // Arrange
        SignUpVo signUpVo = new SignUpVo("testId", "testName", "test@test.com", "password123", "testNickname");
        when(memberRepository.existsById(signUpVo.getId())).thenReturn(false);
        when(memberRepository.existsByEmail(signUpVo.getEmail())).thenReturn(false);


        memberServiceImp.singUp(signUpVo);
    }

}
