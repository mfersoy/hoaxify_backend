package com.hoaxify.security.service;

import com.hoaxify.domain.User;
import com.hoaxify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
		User user= userService.getUserByNickname(nickname);
		return UserDetailsImpl.build(user);
	}

}
