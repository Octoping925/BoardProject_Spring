package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    MainService mainService = new MainService();

    @GetMapping("/")
    public String login() {
        // TODO: 세션 존재할 시 자동 로그인 기능 추가
        return "login";
    }

    @PostMapping("/login")
    public String getLogin(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        boolean loginResult = mainService.login(id, password);
        System.out.println(id + " " + password + " " + loginResult);

        if(!loginResult) {
            model.addAttribute("message", "로그인에 실패하였습니다"); // 로그인 상태 메시지
            return "login";
        }

        return "index";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String joinNewMember(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        boolean result = mainService.joinNewMember(id, password);
        return "login";
    }

}
