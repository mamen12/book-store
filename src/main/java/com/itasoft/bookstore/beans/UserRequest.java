package com.itasoft.bookstore.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Setter;

@Data
@JsonInclude(Include.NON_NULL)
public class UserRequest {
	private String email;
	private String password;
	private String username;
	
}
