package com.board.main.domain;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private Long id;

    private String memberId;

    private String password;

    private String nickname;

    private String email;

    private String number;

    private LocalDateTime regidate;

    private Integer level;

    private Integer exp;
}
