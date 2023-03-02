package com.board.main.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String userId;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Column(columnDefinition = "TEXT")
    private String nickName;

    @Column(columnDefinition = "TEXT")
    private String eMail;

    @Column(columnDefinition = "TEXT")
    private String number;

    private LocalDateTime regidate;

    private Integer level;

    private Integer exp;
}
