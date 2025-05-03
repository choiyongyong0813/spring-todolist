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

    @GetMapping // HTTP GET 요청을 처리함 → GET /users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository. // JPA를 통해 DB에서 모든 User 엔티티를 조회
                findAll().
                stream(). // List<User>를 스트림으로 변환
                        map(user -> UserResponseDTO.builder(). // 각 User를 UserResponseDTO로 변환
                        id(user.getId()).              // ID 설정
                        email(user.getEmail()).        // 이메일 설정
                        nickname(user.getNickname()).  // 닉네임 설정
                        build()).                      // DTO 객체 생성
                        collect(Collectors.toList()); // 모든 DTO를 리스트로 수집 후 반환
    }



    @GetMapping("/{id}") // GET /users/{id} 요청을 처리하는 메서드
    public UserResponseDTO getUserById(@PathVariable Long id) {
        // URL 경로에서 {id} 값을 Long 타입으로 받아옴
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. ID: " + id));
        // 해당 ID의 유저를 데이터베이스에서 조회하고, 없으면 예외 발생

        return UserResponseDTO.builder()
                .id(user.getId()) // 조회된 유저의 ID를 응답 DTO에 세팅
                .email(user.getEmail()) // 조회된 유저의 이메일을 응답 DTO에 세팅
                .nickname(user.getNickname()) // 조회된 유저의 닉네임을 응답 DTO에 세팅
                .build(); // UserResponseDTO 객체 생성 및 반환
    }

    @GetMapping("/email")
    public UserResponseDTO getUserByEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 유저가 없습니다."));

        return UserResponseDTO.builder()
                .id(user.getId()) // 조회된 유저의 ID
                .email(user.getEmail()) // 조회된 유저의 이메일
                .nickname(user.getNickname()) // 조회된 유저의 닉네임
                .build();
    }
}
