package com.board.main.service;

import com.board.main.domain.User;
import com.board.main.domain.UserDTO;
import com.board.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;
    @Override
    public Long signUp(UserDTO userDTO) {

        if (!validateDuplicate(userDTO)) {
            System.out.println("이미 존재하는 회원입니다.");
            return -1L;
        }

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setNumber(userDTO.getEMail());
        user.setPassword(userDTO.getPassword());
        user.setNickName(userDTO.getNickName());
        user.setRegidate(LocalDateTime.now());
        user.setLevel(0);
        user.setExp(0);

        System.out.println(user.getLevel());

        userRepository.save(user);

        return user.getId();
    }

    private boolean validateDuplicate(UserDTO userDTO) {
        User user = userRepository.findByUserId(userDTO.getUserId());
        return user == null;
    }

    @Override
    public User findByUserId() {
        return null;
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
