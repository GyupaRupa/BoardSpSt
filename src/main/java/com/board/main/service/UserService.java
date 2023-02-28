package com.board.main.service;

import com.board.main.domain.User;
import com.board.main.domain.UserDTO;
import com.board.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    Long signUp(UserDTO userDTO);

    User findByUserId();

    User findByEmail();

    User findById();
}
