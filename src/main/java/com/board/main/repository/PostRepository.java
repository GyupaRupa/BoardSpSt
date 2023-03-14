package com.board.main.repository;

import com.board.main.domain.BoardType;
import com.board.main.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop10ByOrderByRegidateDesc();

    Page<Post> findAllByBoardType(Pageable pageable, BoardType boardType);
}