package com.project.chaekbang.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;

    private String description;

    private String imgUrl;

    private String author;

    private String publisher;

    @OneToMany(mappedBy="book")
    private List<UserBook> userBooks = new ArrayList<>();

}
