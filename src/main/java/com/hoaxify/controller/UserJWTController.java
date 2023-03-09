package com.hoaxify.controller;

import com.hoaxify.dto.UserDTO;
import com.hoaxify.dto.response.ResponceMessage;
import com.hoaxify.dto.response.GenericResponse;
import com.hoaxify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class UserJWTController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(ResponceMessage.REGISTER_USER_SUCCESS_MESSAGE);
        genericResponse.setSuccess(true);

        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);

    }


}