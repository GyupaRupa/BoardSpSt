package com.board.main.service;

import com.board.main.domain.Comment;

import java.util.List;

public interface CommentService {
    Long comment(String writer, String content, Long postId);

    List<Comment> findAllByPostId(Long postId);
}
