package com.hoaxify.service;

import com.hoaxify.domain.Role;
import com.hoaxify.domain.User;
import com.hoaxify.domain.enums.RoleType;
import com.hoaxify.dto.UserDTO;
import com.hoaxify.exception.ConflictException;
import com.hoaxify.exception.ResourceNotFoundException;
import com.hoaxify.exception.message.ErrorMessage;
import com.hoaxify.mapper.UserMapper;
import com.hoaxify.repository.RoleRepository;
import com.hoaxify.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMap;
     PasswordEncoder passwordEncoder;
    private final RoleService roleService;



    public UserService(UserRepository userRepository, UserMapper userMap,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.userMap = userMap;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.roleService = roleService;
    }



    public void saveUser(UserDTO userDTO){

        if (userRepository.existsByNickname(userDTO.getNickname())) {
            throw new ConflictException(String.format(ErrorMessage.NICKNAME_ALREADY_EXİST, userDTO.getNickname()));
        }

        Role rol = roleService.findByType(RoleType.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(rol);



      User user = new User();

      user.setUsername(userDTO.getUsername());
      user.setNickname(userDTO.getNickname());

      user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));

      user.setPasswordRepeat(userDTO.getPasswordRepeate());

      userRepository.save(user);
    }

    public User getUserByNickname(String nickname){
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, nickname)));
        return user;
    }

}
