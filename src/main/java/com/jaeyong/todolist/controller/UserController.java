package com.jaeyong.todolist.controller;

import com.jaeyong.todolist.domain.User;
import com.jaeyong.todolist.dto.UserRequestDTO;
import com.jaeyong.todolist.dto.UserResponseDTO;
import com.jaeyong.todolist.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/*
 * User 관련 요청을 처리하는 컨트롤러
 * HTTP 요청을 받고, Repository나 Service와 연결해서 응답을 반환
 */

@RestController //Rest API선언 Json 응답처리
@RequiredArgsConstructor //생성자 자동 주입
@RequestMapping("/users") //url기본 경로 설정
public class UserController {

    // UserRepository를 생성자를 통해 의존성 주입
    private final UserRepository userRepository;

    @PostMapping
    public User createUser(@Valid @RequestBody UserRequestDTO requestDTO) {
        User user = User.builder().email(requestDTO.getEmail()).password(requestDTO.getPassword()).nickname(requestDTO.getNickname()).build();
        return userRepository.save(user); // JPA save로 DB에 저장 → 저장된 User 객체 반환
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> UserResponseDTO.builder().id(user.getId()).email(user.getEmail()).nickname(user.getNickname()).build()).collect(Collectors.toList());
    }

}
