package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.service.MainService;
import com.octoping.boardproject_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/login")
    public String login() {
        return "/members/login";
    }

    @PostMapping("/members/login")
    public String getLogin(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        boolean loginResult = memberService.login(id, password);
        System.out.println(id + " " + password + " " + loginResult);

        if(!loginResult) {
            model.addAttribute("message", "로그인에 실패하였습니다"); // 로그인 상태 메시지
            return "/members/login";
        }

        return "redirect:/movie/movieList";
    }

    @GetMapping("/members/signUp")
    public String signUp() {
        return "/members/signUp";
    }

    @PostMapping("/members/signUp")
    public ModelAndView joinNewMember(@RequestParam("id") String id, @RequestParam("password") String password) {
        ModelAndView mav = new ModelAndView();
        boolean signupResult = memberService.joinNewMember(id, password);
        System.out.println(id + " " + password + signupResult);

        if(!signupResult) { // TODO: 새로고침 않고 alert로 실패 메시지 띄우도록 수정
            mav.setViewName("redirect:/members/signUp");
            mav.addObject("message", "회원가입에 실패하였습니다"); // TODO: 회원가입 실패 이유 상세하게 표시하도록 수정
            return mav;
        }

        mav.setViewName("redirect:/members/login");
        return mav;
    }
}
