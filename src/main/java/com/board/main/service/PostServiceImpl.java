package com.board.main.service;

import com.board.main.domain.Post;
import com.board.main.domain.PostDTO;
import com.board.main.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Long post(String writer, String title, String content, Integer boardType) {
        Post post = new Post();

        post.setWriter(writer);
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
    public List<Post> findAll() {
        List<Post> postList = postRepository.findAll();

        return postList;
    }
}
