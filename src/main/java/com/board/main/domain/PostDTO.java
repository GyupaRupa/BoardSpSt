package com.board.main.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
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
