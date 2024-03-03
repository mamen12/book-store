package com.itasoft.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itasoft.bookstore.beans.UserRequest;
import com.itasoft.bookstore.beans.UserResponse;
import com.itasoft.bookstore.entity.UserInfo;
import com.itasoft.bookstore.repo.UserRepository;
import com.itasoft.bookstore.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public String addUser(UserRequest userRq) {
		if (userRepo.countEmailUser(userRq.getEmail()) > 0) {
			return "Email Already Exists";
		}else {
			UserInfo userInfo = new UserInfo().builder()
					.email(userRq.getEmail())
					.name(userRq.getUsername())
					.password(encoder.encode(userRq.getPassword()))
					.build();
	        userRepo.save(userInfo); 
	        return "User Added Successfully"; 
		}
	}

	@Override
	public void updateLastLogin(String userName) {
		try {
			UserInfo user = userRepo.findByName(userName).orElseThrow();
			user.setLastLogin(new Date());
			
			userRepo.save(user);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<UserResponse> getAllUsers(String username) {
		List<UserResponse> rs = new ArrayList<UserResponse>();
		List<UserInfo> users = new ArrayList<>(); 
		try {
			UserInfo info = userRepo.findByName(username).orElseThrow();
			if (info.getCreatedby().equals("SYSTEM")) {
				users = userRepo.findAll();
				
				rs = users.stream().map(
						u -> new UserResponse().builder()
							.email(u.getEmail())
							.name(u.getName())
							.lastLogin(u.getLastLogin())
							.isActive(u.getDeletedAt() != null ? 0  :1)
							.build()
						).collect(Collectors.toList());
						
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rs;
	}
	
	
	
	
}
