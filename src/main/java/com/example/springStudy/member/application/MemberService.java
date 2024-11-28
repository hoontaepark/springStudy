package com.example.springStudy.member.application;

import com.example.springStudy.member.vo.EmailCheckVo;
import com.example.springStudy.member.vo.IdCheckVo;
import com.example.springStudy.member.vo.SignUpVo;

public interface MemberService {

    void singUp(SignUpVo signUpVo);

    void idCheck(IdCheckVo idCheckVo);

    void emailCheck(EmailCheckVo emailCheckVo);

}
