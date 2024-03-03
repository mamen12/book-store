package com.itasoft.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itasoft.bookstore.beans.Request;
import com.itasoft.bookstore.beans.Response;
import com.itasoft.bookstore.beans.UserRequest;
import com.itasoft.bookstore.constant.ApiResponse;
import com.itasoft.bookstore.service.IUserService;
import com.itasoft.bookstore.util.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    @Autowired
    private JwtService jwtService; 
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager; 
	
	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> loginUser(@RequestBody Request<UserRequest> rq){
    	Response<String> rs = new Response<>();
    	Boolean statusAuthenticated = false;
    	try {
    		Authentication authentication = authenticationManager.authenticate(
        			new UsernamePasswordAuthenticationToken(rq.getRequestPayload().getUsername(), rq.getRequestPayload().getPassword()));
    		if (authentication.isAuthenticated()) {
    			statusAuthenticated = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	if (statusAuthenticated) { 
    		rs.setData(jwtService.generateToken(rq.getRequestPayload().getUsername()));
    		if (rs.getData() != null) {
				userService.updateLastLogin(rq.getRequestPayload().getUsername());
			}
    		rs.setStatusResponse(ApiResponse.SUCCESS);
    	} else { 
    		rs.setStatusResponse(ApiResponse.UNAUTHORIZED);
    	} 
    	return rs;
    }
}
