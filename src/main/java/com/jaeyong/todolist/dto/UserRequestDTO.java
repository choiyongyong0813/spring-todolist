package com.jaeyong.todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @Email(message = "이메일 형식이 틀려!!")
    @NotBlank(message = "이메일은 필수야!!")
    private String email;

    @NotBlank(message = "비밀번호는 필수야!!")
    private String password;

    @NotBlank(message = "별명은 필수야!!")
    private String nickname;
}
