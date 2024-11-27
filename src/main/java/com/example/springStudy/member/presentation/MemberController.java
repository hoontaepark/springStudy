package com.example.springStudy.member.presentation;


import com.example.springStudy.global.common.response.BaseResponse;
import com.example.springStudy.member.application.MemberServiceImp;
import com.example.springStudy.member.vo.SignUpVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImp memberServiceImp;

    @PostMapping("/springstody/member/signUp") //회원가입
    public BaseResponse<Void> signUp(@RequestBody SignUpVo signUpVo){

        memberServiceImp.singUp(signUpVo);
        return new BaseResponse<>();

    }


}
