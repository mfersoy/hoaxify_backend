package com.hoaxify.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {

    @NotBlank(message = "Please provide a valid email")
    private String nickname;

    @NotBlank(message = "Please Provide a password")
    private String password;

}
