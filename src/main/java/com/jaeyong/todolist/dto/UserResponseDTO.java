package com.jaeyong.todolist.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String nickname;
}
