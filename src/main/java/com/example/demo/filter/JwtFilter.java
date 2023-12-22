package com.example.demo.filter;

import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);


        String token;
        if(header!=null && header.startsWith("Bearer ")) {
            token = header.split(" ")[1];
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtUtils.getUsername(token, secretKey);

        // 인증 과정 수행 회원 엔티티를 받아온다.
        // member.getUsername();
//        String memerUsername = member.getUsername;
//        if(!JwtUtils.validate(token, memberUsername, secretKey)) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        // 인가하는 코드

        filterChain.doFilter(request, response);

    }
}
