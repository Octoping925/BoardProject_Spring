package com.octoping.boardproject_spring.contoller;

import com.octoping.boardproject_spring.domain.Member;
import com.octoping.boardproject_spring.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    MainService mainService = new MainService();

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String getLogin(@RequestParam("id") String id, @RequestParam("password") String password) {
        Member member = mainService.login(id, password);
        System.out.println(id + " " + password + " " + member);
        return "index";
    }

}
