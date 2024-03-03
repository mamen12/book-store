package com.itasoft.bookstore.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itasoft.bookstore.beans.Request;
import com.itasoft.bookstore.beans.Response;
import com.itasoft.bookstore.beans.UserRequest;
import com.itasoft.bookstore.beans.UserResponse;
import com.itasoft.bookstore.service.IUserService;


@RestController
@RequestMapping("/api") 
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    
  
    String pattern = "yyyy-MM-dd HH:mm:ss";
    
    @GetMapping("/welcome") 
    public String welcome() { 
        return "Welcome this endpoint is not secure"; 
    } 
  
    @PostMapping("/users") 
    public Response<List<UserResponse>> addNewUser(@RequestBody Request<UserRequest> rq) { 
    	Response<List<UserResponse>> rs = new Response<List<UserResponse>>();
    	
    	List<UserResponse> data = userService.getAllUsers(rq.getRequestPayload().getUsername());
    	if (!data.isEmpty()) {
			rs.setData(data);
			rs.setMessage("Succcess");
		}else {
			rs.setMessage("Data Not Found");
		}
    	return rs;
    	
    } 
  
    
	
    
  

  
} 
