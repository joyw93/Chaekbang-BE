package com.project.chaekbang.service;

import com.project.chaekbang.domain.User;
import com.project.chaekbang.dto.user.CreateUserDto;
import com.project.chaekbang.dto.user.LoginUserDto;
import com.project.chaekbang.dto.user.UserResponseDto;
import com.project.chaekbang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<UserResponseDto> signup(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());

        return ResponseEntity.ok(userResponseDto);
    }

    public Long login(HttpServletRequest request, LoginUserDto loginUserDto) throws Exception {
        String email = loginUserDto.getEmail();
        String password = loginUserDto.getPassword();

        Optional<User> userByEmail = userRepository.findUserByEmail(email);

        if(userByEmail.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 계정입니다.");
        }

        if(userByEmail.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser",userByEmail.get());
            return userByEmail.get().getId();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "패스워드가 일치하지 않습니다.");
        }

    }

    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return "로그아웃 성공";
        }
        return "로그아웃 실패";
    }

}
