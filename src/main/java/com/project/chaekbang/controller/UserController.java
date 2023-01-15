package com.project.chaekbang.controller;

import com.project.chaekbang.domain.User;
import com.project.chaekbang.dto.user.CreateUserDto;
import com.project.chaekbang.dto.user.LoginUserDto;
import com.project.chaekbang.dto.user.UserResponseDto;
import com.project.chaekbang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers() {
        return "users";
    }

    @GetMapping("/loginTest")
    public String test(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println(session.getId());
        return null;
    }


    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody CreateUserDto createUserDto) {
        return userService.signup(createUserDto);
    }

    @PostMapping("/login")
    public Long login(HttpServletRequest request, @RequestBody LoginUserDto loginUserDto) throws Exception {
        return userService.login(request, loginUserDto);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
    }

}
