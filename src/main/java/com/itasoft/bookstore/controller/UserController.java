package com.itasoft.bookstore.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itasoft.bookstore.beans.Request;
import com.itasoft.bookstore.beans.Response;
import com.itasoft.bookstore.beans.UserRequest;
import com.itasoft.bookstore.beans.UserResponse;
import com.itasoft.bookstore.filter.HttpLoggingFilter;
import com.itasoft.bookstore.service.impl.AuthService;
import com.itasoft.bookstore.service.impl.UserServiceImpl;
import com.itasoft.bookstore.util.JwtService;


@RestController
@RequestMapping("/api") 
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
    @Autowired
    private AuthService service; 
  
    @Autowired
    private JwtService jwtService; 
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private AuthenticationManager authenticationManager; 
  
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
  
    
	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> loginUser(@RequestBody Request<UserRequest> rq){
    	Response<String> rs = new Response<>();
    	rs.setStatus(false);
    	try {
    		Authentication authentication = authenticationManager.authenticate(
        			new UsernamePasswordAuthenticationToken(rq.getRequestPayload().getUsername(), rq.getRequestPayload().getPassword()));
    		if (authentication.isAuthenticated()) {
    			rs.setStatus(true);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	if (rs.getStatus()) { 
    		rs.setData(jwtService.generateToken(rq.getRequestPayload().getUsername()));
    		if (rs.getData() != null) {
				userService.updateLastLogin(rq.getRequestPayload().getUsername());
			}
    		rs.setMessage("Succesfully Login");
    	} else { 
    		rs.setMessage("Unauthorized");
    	} 
    	return rs;
    }
    
  

  
} 
