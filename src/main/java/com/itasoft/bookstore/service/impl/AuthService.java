package com.itasoft.bookstore.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itasoft.bookstore.beans.UserRequest;
import com.itasoft.bookstore.entity.UserInfo;
import com.itasoft.bookstore.repo.UserRepository;
import com.itasoft.bookstore.service.IUserService;
import com.itasoft.bookstore.util.UserInfoDetails;

@Service
public class AuthService implements UserDetailsService  {

	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = userRepo.findByName(username);
		
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
	}
}
