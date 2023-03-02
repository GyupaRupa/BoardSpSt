package com.board.main.service;

import com.board.main.domain.Comment;
import com.board.main.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Override
    public Long comment(String writer, String content, Long postId) {
        Comment comment = new Comment();

        comment.setPostId(postId);
        comment.setWriter(writer);
        comment.setContent(content);
        comment.setRegidate(LocalDateTime.now());
        comment.setLikeCnt(0);
        comment.setHateCnt(0);

        commentRepository.save(comment);

        return comment.getId();
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments;
    }
}
