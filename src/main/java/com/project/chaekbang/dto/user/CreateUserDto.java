package com.project.chaekbang.dto.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {
    private String name;
    private String email;
    private String password;
}
