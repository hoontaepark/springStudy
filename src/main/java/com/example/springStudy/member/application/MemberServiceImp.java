package com.example.springStudy.member.application;

import com.example.springStudy.global.common.exception.CustomException;
import com.example.springStudy.global.common.response.BaseResponseCode;
import com.example.springStudy.member.domain.Member;
import com.example.springStudy.member.infrastructure.MemberRepository;
import com.example.springStudy.member.vo.SignUpVo;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImp {

    private final MemberRepository memberRepository;

    public String hashPassword(String Password) { //PW 해시 암호화
        Password = new BCryptPasswordEncoder().encode(Password);
        return Password;
    }

    public void singUp(SignUpVo signUpVo){

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        if (memberRepository.existsByName(signUpVo.getName())){
            throw new CustomException(BaseResponseCode.EXIST_NAME);
        }else if (memberRepository.existsByEmail(signUpVo.getEmail())){
            throw new CustomException(BaseResponseCode.EXIST_EMAIL);
        }

        Member member = Member.builder()
                .name(signUpVo.getName())
                .email(signUpVo.getEmail())
                .password(hashPassword(signUpVo.getPassword()))
                .uuid(uuidString)
                .build();
        memberRepository.save(member);


    }

}
