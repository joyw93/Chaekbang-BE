package com.project.chaekbang.repository;

import com.project.chaekbang.domain.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    public Optional<UserBook> findByBookId(Long bookId);

}
