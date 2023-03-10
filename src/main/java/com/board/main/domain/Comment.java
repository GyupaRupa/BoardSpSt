package com.board.main.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Member author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer likeCnt;

    private Integer hateCnt;

    private LocalDateTime regidate;
}
