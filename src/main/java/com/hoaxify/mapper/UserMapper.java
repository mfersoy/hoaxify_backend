package com.hoaxify.mapper;

import com.hoaxify.dto.UserDTO;
import org.mapstruct.Mapper;
import com.hoaxify.domain.User;


@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO userToUserDTO(User user);

    User userDTOTOUser(UserDTO userDTO);

}
