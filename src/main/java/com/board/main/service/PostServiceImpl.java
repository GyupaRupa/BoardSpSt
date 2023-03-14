package com.board.main.service;

import com.board.main.domain.BoardType;
import com.board.main.domain.Member;
import com.board.main.domain.Post;
import com.board.main.domain.PostDTO;
import com.board.main.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private final PostRepository postRepository;

    @Override
    public Long post(Member author, String title, String content, BoardType boardType) {
        Post post = new Post();

        post.setAuthor(author);
        post.setTitle(title);
        post.setContent(content);
        post.setHateCnt(0);
        post.setLikeCnt(0);
        post.setRegidate(LocalDateTime.now());
        post.setBoardType(boardType);

        postRepository.save(post);

        return post.getId();
    }

    @Override
    public Post findById(Long id) throws Exception {
        Optional<Post> postOptional = postRepository.findById(id);

        if(postOptional.isPresent()) {
            Post post = postOptional.get();
            return post;
        } else {
            throw new Exception();
        }

    }

    @Override
    public List<Post> getBoardPosts(int crtPage, int size, BoardType boardType) throws Exception {
//        Page<Post> postPage = postRepository.findAll(PageRequest.of(crtPage, size, Sort.by(Sort.Direction.DESC, "regidate")));
        Page<Post> postPage = postRepository.findAllByBoardType(PageRequest.of(crtPage, size, Sort.by(Sort.Direction.DESC, "regidate")), boardType);
        if (postPage.isEmpty()) {
            throw new Exception();
        } else {
            return postPage.getContent();
        }
    }

//    @Override
//    public List<Post> findAll() {
//        List<Post> postList = postRepository.findAll();
//
//        return postList;
//    }

    @Override
    public List<Post> findTop10() {
        List<Post> postList = postRepository.findTop10ByOrderByRegidateDesc();

        return postList;
    }
}
