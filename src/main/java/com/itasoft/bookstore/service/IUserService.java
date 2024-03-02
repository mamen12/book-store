package com.itasoft.bookstore.service;

import java.util.List;

import com.itasoft.bookstore.beans.UserRequest;
import com.itasoft.bookstore.beans.UserResponse;

public interface IUserService {

	public String addUser(UserRequest userRq);

	public void updateLastLogin(String userName);
	
	public List<UserResponse> getAllUsers(String username);
	
}
