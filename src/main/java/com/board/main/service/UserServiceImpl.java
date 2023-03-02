package com.board.main.service;

import com.board.main.domain.User;
import com.board.main.domain.UserDTO;
import com.board.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long signUp(String userId, String password, String nickName, String eMail, String number) {

        if (!validateDuplicate(userId)) {
            System.out.println("이미 존재하는 회원입니다.");
            return -1L;
        }

        System.out.println(userId + password + nickName + eMail + number);

        User user = new User();
        user.setUserId(userId);
        user.setNumber(eMail);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickName(nickName);
        user.setNumber(number);
        user.setRegidate(LocalDateTime.now());
        user.setLevel(0);
        user.setExp(0);

        userRepository.save(user);

        return user.getId();
    }

    private boolean validateDuplicate(String userId) {
        User user = userRepository.findByUserId(userId);
        return user == null;
    }

    @Override
    public User findByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new RuntimeException("해당하는 유저가 없습니다");
        return user;
    }

    @Override
    public User findByEmail() {
        return null;
    }

    @Override
    public User findById() {
        return null;
    }
}
