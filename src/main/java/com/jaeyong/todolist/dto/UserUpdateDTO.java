package com.jaeyong.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank(message = "닉네임은 필수 입니다!")
    private String nickname;
    @NotBlank(message = "비밀번호는 필수 입니다!")
    private String password;
}
