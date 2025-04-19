package com.jaeyong.todolist.controller;

import com.jaeyong.todolist.domain.User;
import com.jaeyong.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public User createUser(@RequestBody User user) {
        return  userRepository.save(user); // JPA save로 DB에 저장 → 저장된 User 객체 반환
    }
}
