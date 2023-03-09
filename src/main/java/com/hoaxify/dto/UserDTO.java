package com.hoaxify.dto;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    @Size(max = 30, message = "Size is exceeded")
    @NotBlank(message = "Please provide your name!")
    private String username;

    @Size(max = 30, message = "Size is exceeded")
    @NotBlank(message = "Please provide your Nick name!")
    private String nickname;

    @Size(min = 4, max = 20, message = "Please Provide Correct Size for Password")
    @NotBlank(message = "Please provide your password!")
    private String password;

    @Size(min = 4, max = 20, message = "Please Provide Correct Size for Password")
    @NotBlank(message = "Please write you password again!")
    private String passwordRepeate;

}
