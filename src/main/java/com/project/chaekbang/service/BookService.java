package com.project.chaekbang.service;
import com.project.chaekbang.domain.Book;
import com.project.chaekbang.domain.User;
import com.project.chaekbang.domain.UserBook;
import com.project.chaekbang.dto.book.CreateBookDto;
import com.project.chaekbang.repository.BookRepository;
import com.project.chaekbang.repository.UserBookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;

    @Transactional
    public Long addBook(HttpServletRequest request, CreateBookDto createBookDto) {
        // 유저 로그인 확인
        HttpSession session = request.getSession(false);
        User currentUser = new User();
        if(session == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "로그인이 필요합니다.");
        } else {
            currentUser = (User)session.getAttribute("loginUser");
        }

        Book book = new Book();
        book.setName(createBookDto.getName());
        book.setAuthor(createBookDto.getAuthor());
        book.setImgUrl(createBookDto.getImgUrl());
        book.setPublisher(createBookDto.getPublisher());
        bookRepository.save(book);

        UserBook userBook = new UserBook();
        userBook.setBook(book);
        userBook.setUser(currentUser);
        userBookRepository.save(userBook);

        return book.getId();
    }

    @Transactional
    public Long deleteBook(HttpServletRequest request, Long bookId) {
        HttpSession session = request.getSession(false);
        User currentUser = new User();
        if(session == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "로그인이 필요합니다.");
        } else {
            currentUser = (User)session.getAttribute("loginUser");
        }

        Optional<Book> book = bookRepository.findById(bookId);
        Optional<UserBook> userBook = userBookRepository.findByBookId(bookId);

        book.ifPresent(bookRepository::delete);
        userBook.ifPresent(userBookRepository::delete);

        return bookId;
    }
}
