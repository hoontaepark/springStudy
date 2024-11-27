package com.example.springStudy.member.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpVo {

    private String id;
    private String password;
    private String name;
    private String nickname;
    private String email;
}
