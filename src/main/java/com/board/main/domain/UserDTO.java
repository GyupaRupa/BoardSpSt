package com.board.main.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private String userId;

    private String password;

    private String nickName;

    private String eMail;

    private String number;

    private LocalDateTime regidate;

    private Integer level;

    private Integer exp;
}
