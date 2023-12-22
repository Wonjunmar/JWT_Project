package com.example.demo.member.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignLogDto {

    String email;

    String password;

}


