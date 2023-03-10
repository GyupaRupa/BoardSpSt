package com.board.main.service;

import com.board.main.domain.Comment;
import com.board.main.domain.Member;
import com.board.main.domain.Post;

import java.util.List;

public interface CommentService {
    Long comment(Member author, String content, Post post);

    List<Comment> findAllByPostId(Long postId);
}
