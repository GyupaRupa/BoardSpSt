package com.board.main;

import com.board.main.domain.*;
import com.board.main.service.CommentService;
import com.board.main.service.PostService;
import com.board.main.service.MemberService;
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
    private MemberService memberService;
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

    @GetMapping("/member/signin")
    public ModelAndView getSignInPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signin.html");
        return mv;
    }

//    @PostMapping("/member/signin")
//    public ModelAndView signIn(String memberId, String password) {
//        System.out.println("\n여기야!!\n");
//        System.out.println(memberId);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("redirect:/");
//
//        return mv;
//    }

    @GetMapping("/member/signup")
    public ModelAndView signUp() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signup.html");
        return mv;
    }

    @PostMapping("/member/signup")
    public ModelAndView doSignUp(String memberId, String password, String nickname, String email, String number) {
        memberService.signUp(memberId, password, nickname, email, number);

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
