package com.example.demo.member.controller;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.MemberDto;
import com.example.demo.member.model.SignLogDto;
import com.example.demo.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    MemberService memberService;

    private AuthenticationManager authenticationManager;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup (@RequestBody SignLogDto signLogDto) {
        memberService.signup(signLogDto);
        return ResponseEntity.ok().body("ok");
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(String email, String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok().body(((Member)authentication.getPrincipal()).getEmail());
    }


    @RequestMapping(method = RequestMethod.GET, value = "/mypage")
    public ResponseEntity mypage() {
        return ResponseEntity.ok().body("Mypage~");
    }




    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(MemberDto memberDto) {
        memberService.create(memberDto);

        return ResponseEntity.ok().body("생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {

        return ResponseEntity.ok().body(memberService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(String email) {

        return ResponseEntity.ok().body(memberService.read(email));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(MemberDto memberDto) {
        memberService.update(memberDto);

        return ResponseEntity.ok().body("수정");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(Integer idx) {
        memberService.delete(idx);
        return ResponseEntity.ok().body("삭제");
                
    }
}
