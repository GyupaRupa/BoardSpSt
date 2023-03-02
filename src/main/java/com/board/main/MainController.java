package com.board.main;

import com.board.main.domain.*;
import com.board.main.service.CommentService;
import com.board.main.service.PostService;
import com.board.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");

//        User user = userService.findByUserId();

        mv.addObject("temp", "hello!!!");
        return mv;
    }

    @GetMapping("/member/signup")
    public ModelAndView getLoginPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signup.html");
        return mv;
    }

    @PostMapping("/member/signup")
    public ModelAndView login(String userId, String password) {


        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("/member/signup")
    public ModelAndView signUp() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signup.html");
        return mv;
    }

    @PostMapping("/member/signup")
    public ModelAndView doSignUp(String userId, String password, String nickName, String eMail, String number) {
        userService.signUp(userId, password, nickName, eMail, number);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");

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

    @GetMapping("/board/free/{id}")
    public ModelAndView getFreePost(@PathVariable("id") Long id) {
        try {
            Post post = postService.findById(id);
            ModelAndView mv = new ModelAndView();

            List<Comment> commentList = commentService.findAllByPostId(id);

            mv.setViewName("/board/post.html");
            mv.addObject(post);
            mv.addObject(commentList);

            return mv;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/board/free/cmtcreate/{id}")
    public ModelAndView cmtToPost(@PathVariable("id") Long id, String content) {
        commentService.comment("_", content, id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free/{id}");

        return mv;
    }

    @GetMapping("/board/free/create")
    public ModelAndView createFreePost() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/create.html");

        return mv;
    }

    @PostMapping("/board/free/create")
    public ModelAndView postFreeBoard(String title, String content) {
        postService.post("_", title, content, 1);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free");

        return mv;
    }

}
