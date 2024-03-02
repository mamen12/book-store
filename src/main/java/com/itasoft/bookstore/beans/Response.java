package com.itasoft.bookstore.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"code", "message", "data"})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T>{
	
	@JsonProperty(value = "code")
	private Boolean status;
	
	@JsonProperty(value = "message")
	private String message;
	
	private T data;
	
}
