package com.board.main.service;

import com.board.main.domain.User;
import com.board.main.domain.UserDTO;
import com.board.main.domain.UserRole;
import com.board.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long signUp(String userId, String password, String nickName, String eMail, String number) {

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
    public User findByEmail() {
        return null;
    }

    @Override
    public User findById() {
        return null;
    }
}
