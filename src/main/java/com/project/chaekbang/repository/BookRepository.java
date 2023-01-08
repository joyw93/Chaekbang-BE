package com.project.chaekbang.repository;

import com.project.chaekbang.domain.Book;
import com.project.chaekbang.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
