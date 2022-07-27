package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.service.MainService;
import com.octoping.boardproject_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members/login")
    public String getLogin(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        boolean loginResult = memberService.login(id, password);
        System.out.println(id + " " + password + " " + loginResult);

        if(!loginResult) {
            model.addAttribute("message", "로그인에 실패하였습니다"); // 로그인 상태 메시지
            return "/members/login";
        }

//        model.addAttribute("movies", mainService.movieRepository.findAll());
        return "/main/index";
    }

    @GetMapping("/members/signUp")
    public String signUp() {
        return "/members/signUp";
    }

    @PostMapping("/members/signUp")
    public String joinNewMember(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        boolean result = memberService.joinNewMember(id, password);
        return "/members/login";
    }
}
