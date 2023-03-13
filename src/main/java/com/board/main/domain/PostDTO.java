package com.board.main.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Long id;

    private String writer;

    private String title;

    private String content;

    private Integer likeCnt;

    private Integer hateCnt;

    //
    private Integer boardType;

    private LocalDateTime regidate;
}
