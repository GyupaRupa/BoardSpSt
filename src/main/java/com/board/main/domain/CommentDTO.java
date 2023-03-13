package com.board.main.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;

    private Post post;

    private Member author;

    private String content;

    private Integer likeCnt;

    private Integer hateCnt;

    private LocalDateTime regidate;
}
