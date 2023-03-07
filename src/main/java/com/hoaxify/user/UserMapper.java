package com.hoaxify.user;

import org.mapstruct.Mapper;
import com.hoaxify.user.UserDTO;
import com.hoaxify.user.User;


@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO userToUserDTO(User user);

    User userDTOTOUser(UserDTO userDTO);

}
