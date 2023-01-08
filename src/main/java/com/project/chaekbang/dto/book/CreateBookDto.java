package com.project.chaekbang.dto.book;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBookDto {
    private String name;
    private String description;
    private String imgUrl;
    private String author;
    private String publisher;

}
