package com.example.springStudy.member.application;

import com.example.springStudy.global.common.exception.CustomException;
import com.example.springStudy.global.common.response.BaseResponseCode;
import com.example.springStudy.member.domain.Member;
import com.example.springStudy.member.infrastructure.MemberRepository;
import com.example.springStudy.member.vo.EmailCheckVo;
import com.example.springStudy.member.vo.IdCheckVo;
import com.example.springStudy.member.vo.SignUpVo;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;

    public String hashPassword(String Password) { //PW 해시 암호화
        Password = new BCryptPasswordEncoder().encode(Password);
        return Password;
    }


    @Override
    @Transactional
    public void singUp(SignUpVo signUpVo){

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        if (memberRepository.existsById(signUpVo.getId())){
            throw new CustomException(BaseResponseCode.EXIST_ID);
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

    @Override
    public void idCheck(IdCheckVo idCheckVo){
        if (!memberRepository.existsById(idCheckVo.getId())){
            throw new CustomException(BaseResponseCode.EXIST_ID);
        }

    }

    @Override
    public void emailCheck(EmailCheckVo emailCheckVo) {
        if (!memberRepository.existsByEmail(emailCheckVo.getEmail())) {
            throw new CustomException(BaseResponseCode.EXIST_EMAIL);
        }
    }

}
