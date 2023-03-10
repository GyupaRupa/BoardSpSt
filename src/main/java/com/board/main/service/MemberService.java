package com.board.main.service;

import com.board.main.domain.Member;

public interface MemberService {
    Long signUp(String memberId, String password, String nickname, String email, String number);

    Member findByEmail();

    Member findById();
}
