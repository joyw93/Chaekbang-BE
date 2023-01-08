package com.project.chaekbang.controller;

import com.project.chaekbang.dto.book.CreateBookDto;
import com.project.chaekbang.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public Long addBook(HttpServletRequest request, @RequestBody CreateBookDto createBookDto) {
        return bookService.addBook(request, createBookDto);
    }

    @DeleteMapping("/{bookId}")
    public Long deleteBook(HttpServletRequest request, @PathVariable Long bookId) {
        return bookService.deleteBook(request, bookId);
    }
}
