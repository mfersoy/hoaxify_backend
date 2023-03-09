package com.hoaxify.controller;

import com.hoaxify.dto.request.RegistirationRequest;
import com.hoaxify.dto.request.LoginRequest;
import com.hoaxify.dto.response.LoginResponse;
import com.hoaxify.dto.response.ResponceMessage;
import com.hoaxify.dto.response.GenericResponse;
import com.hoaxify.security.jwt.JwtUtils;
import com.hoaxify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserJWTController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody RegistirationRequest registirationRequest) {
        userService.saveUser(registirationRequest);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(ResponceMessage.REGISTER_USER_SUCCESS_MESSAGE);
        genericResponse.setSuccess(true);

        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getNickname(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(jwtToken);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}