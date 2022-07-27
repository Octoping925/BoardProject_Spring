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
    private MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String main() {
        // TODO: 세션 존재할 시 자동 로그인 기능 추가
        return "members/login";
    }
}
