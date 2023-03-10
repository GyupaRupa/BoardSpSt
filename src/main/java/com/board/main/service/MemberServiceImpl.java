package com.board.main.service;

import com.board.main.domain.Member;
import com.board.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long signUp(String memberId, String password, String nickname, String email, String number) {

        Member member = new Member();
        member.setMemberId(memberId);
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        member.setNickname(nickname);
        member.setNumber(number);
        member.setRegidate(LocalDateTime.now());
        member.setLevel(0);
        member.setExp(0);

        memberRepository.save(member);

        return member.getId();
    }

//    @Override
//    public User findByUserId(String userId) {
//        Optional<User> _user = this.userRepository.findByUserId(userId);
//        if (_user.isEmpty()) {
//            throw new RuntimeException();
//        }
//        User user = _user.get();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if ("admin".equals(userId)) {
//            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
//        } else {
//            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
//        }
//        return new User(user.getUserId(), user.getPassword(), authorities);
//
//    }
    @Override
    public Member findByEmail() {
        return null;
    }

    @Override
    public Member findById() {
        return null;
    }
}
