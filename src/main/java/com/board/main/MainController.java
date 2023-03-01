package com.board.main;

import com.board.main.domain.Post;
import com.board.main.domain.PostDTO;
import com.board.main.domain.User;
import com.board.main.domain.UserDTO;
import com.board.main.service.PostService;
import com.board.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");

//        User user = userService.findByUserId();

        mv.addObject("temp", "hello!!!");
        return mv;
    }

    @GetMapping("/member/signup")
    public ModelAndView signUp() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signup.html");
        return mv;
    }

    @GetMapping("/board/free")
    public ModelAndView freeBoard() {
        List<Post> postList = postService.findAll();

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/board/free.html");
        mv.addObject(postList);

        return mv;
    }

    @GetMapping("/board/free/post")
    public ModelAndView postFreeBoard() {


        ModelAndView mv = new ModelAndView();

        mv.setViewName("/board/post.html");

        return mv;
    }

//    @PostMapping("/member/signup")
//    public ModelAndView signUp() {
//
//    }

}
