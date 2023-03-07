package com.hoaxify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private  UserRepository userRepository;
    private UserMapper userMap;
     PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMap) {
        this.userRepository = userRepository;
        this.userMap = userMap;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }



    public void makeUser(UserDTO userDTO){

      User user = new User();

      user.setUsername(userDTO.getUsername());
      user.setNickname(userDTO.getNickname());


      user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));

      user.setPasswordRepeat(userDTO.getPasswordRepeate());

      userRepository.save(user);



    }

}
