package com.hoaxify.service;


import com.hoaxify.domain.Role;
import com.hoaxify.domain.enums.RoleType;
import com.hoaxify.exception.ResourceNotFoundException;
import com.hoaxify.exception.message.ErrorMessage;
import com.hoaxify.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByType(RoleType roleType) {
        return roleRepository.findByType(roleType).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, roleType.name())));
    }
}
