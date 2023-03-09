package com.hoaxify.service;

import com.hoaxify.domain.Role;
import com.hoaxify.domain.User;
import com.hoaxify.domain.enums.RoleType;
import com.hoaxify.dto.request.RegistirationRequest;
import com.hoaxify.exception.ConflictException;
import com.hoaxify.exception.ResourceNotFoundException;
import com.hoaxify.exception.message.ErrorMessage;
import com.hoaxify.mapper.UserMapper;
import com.hoaxify.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMap;
     PasswordEncoder passwordEncoder;
    private final RoleService roleService;



    public UserService(UserRepository userRepository, UserMapper userMap,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.userMap = userMap;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.roleService = roleService;
    }



    public void saveUser(RegistirationRequest registirationRequest){

        if (userRepository.existsByNickname(registirationRequest.getNickname())) {
            throw new ConflictException(String.format(ErrorMessage.NICKNAME_ALREADY_EXIST, registirationRequest.getNickname()));
        }

        Role rol = roleService.findByType(RoleType.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(rol);



      User user = new User();

      user.setUsername(registirationRequest.getUsername());
      user.setNickname(registirationRequest.getNickname());

      user.setPassword(this.passwordEncoder.encode(registirationRequest.getPassword()));

      user.setPasswordRepeat(registirationRequest.getPasswordRepeate());
      user.setRoles(roles);

      userRepository.save(user);
    }

    public User getUserByNickname(String nickname){
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, nickname)));
        return user;
    }

}
