package com.example.demo.member.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDto {
    Integer idx;

    String email;
    String password;
    String authority;
}


