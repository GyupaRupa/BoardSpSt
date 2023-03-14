package com.board.main;

import com.board.main.domain.*;
import com.board.main.service.CommentService;
import com.board.main.service.PostService;
import com.board.main.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        List<Post> postList = postService.findTop10();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        mv.addObject(postList);

        return mv;
    }

    @GetMapping("/member/signin")
    public ModelAndView getSignIn() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signin.html");
        return mv;
    }

    @GetMapping("/member/signup")
    public ModelAndView getSignUp() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/member/signup.html");
        return mv;
    }

    @PostMapping("/member/signup")
    public ModelAndView PostSignUp(String memberId, String password, String nickname, String email, String number) {
        memberService.signUp(memberId, password, nickname, email, number);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("/board/{id}")
    public ModelAndView getFreeBoardPost(@PathVariable("id") Long id) {
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

    @GetMapping("/board/free")
    public ModelAndView getFreeBoard(String page) throws Exception{
        if (page == null || page.equals("0")) page = "1";
        List<Post> postList = postService.getBoardPosts(Integer.valueOf(page) - 1, 20, BoardType.FREE);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/board/free.html");
        mv.addObject(postList);

        return mv;
    }

    @GetMapping("/board/game")
    public ModelAndView getGameBoard(String page) throws Exception{
        if (page == null || page.equals("0")) page = "1";
        List<Post> postList = postService.getBoardPosts(Integer.valueOf(page) - 1, 10, BoardType.GAME);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/board/game.html");
        mv.addObject(postList);

        return mv;
    }

    @GetMapping("/board/sports")
    public ModelAndView getSportsBoard(String page) throws Exception{
        if (page == null || page.equals("0")) page = "1";
        List<Post> postList = postService.getBoardPosts(Integer.valueOf(page) - 1, 10, BoardType.SPORTS);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("/board/sports.html");
        mv.addObject(postList);

        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board/cmtcreate/{id}")
    public ModelAndView cmtToPost(@PathVariable("id") Long id, String content, Principal principal) {
        Member author = memberService.getUser(principal.getName());
        Post post = null;
        try {
            post = postService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        commentService.comment(author, content, post);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free/{id}");

        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board/free/create")
    public ModelAndView postFreeBoard(String title, String content, Principal principal) {
        Member author = memberService.getUser(principal.getName());
        postService.post(author, title, content, BoardType.FREE);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free");

        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board/game/create")
    public ModelAndView postGameBoard(String title, String content, Principal principal) {
        Member author = memberService.getUser(principal.getName());
        postService.post(author, title, content, BoardType.GAME);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/game");

        return mv;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/board/sports/create")
    public ModelAndView postSportsBoard(String title, String content, Principal principal) {
        Member author = memberService.getUser(principal.getName());
        postService.post(author, title, content, BoardType.SPORTS);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/sports");

        return mv;
    }



    @GetMapping("/board/free/add")
    public ModelAndView addFreeBoardContent() throws Exception {
        Member author = memberService.getUser("tester");
        for(int i=0; i<10; i++) {
            String n = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS"));
            postService.post(author, "자유 게시판 글 " + n, String.valueOf(i), BoardType.FREE);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free");
        return mv;
    }

    @GetMapping("/board/game/add")
    public ModelAndView addGameBoardContent() throws Exception {
        Member author = memberService.getUser("tester");
        for(int i=0; i<10; i++) {
            String n = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS"));
            postService.post(author, "게임 게시판 글 " + n, String.valueOf(i), BoardType.GAME);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free");
        return mv;
    }

    @GetMapping("/board/sports/add")
    public ModelAndView addSportsBoardContent() throws Exception {
        Member author = memberService.getUser("tester");
        for(int i=0; i<10; i++) {
            String n = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS"));
            postService.post(author, "스포츠 게시판 글 " + n, String.valueOf(i), BoardType.SPORTS);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/board/free");
        return mv;
    }

}
