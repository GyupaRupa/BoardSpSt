package com.board.main;

import com.board.main.domain.User;
import com.board.main.domain.UserDTO;
import com.board.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String main() {

        return "index.html";
    }

    @GetMapping("/member/signup")
    public String signUp() {
        return "member/signup.html";
    }

}
