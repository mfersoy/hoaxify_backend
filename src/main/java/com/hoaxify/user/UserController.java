package com.hoaxify.user;

import com.hoaxify.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    private UserService userSevice;


    @PostMapping("/api/1.0/users")
    public ResponseEntity<GenericResponse> createUser(@Valid @RequestBody  UserDTO userDTO){
       userSevice.makeUser(userDTO);
       GenericResponse genericResponse = new GenericResponse("User is created");
       return ResponseEntity.ok(genericResponse);
    }
}
