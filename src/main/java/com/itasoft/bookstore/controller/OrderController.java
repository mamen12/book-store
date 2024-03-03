package com.itasoft.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itasoft.bookstore.beans.OrderDTO;
import com.itasoft.bookstore.beans.Request;
import com.itasoft.bookstore.beans.Response;
import com.itasoft.bookstore.constant.ApiResponse;
import com.itasoft.bookstore.service.IOrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping(path = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Object> orderBook(@RequestBody Request<OrderDTO> rq){
		Response<Object> resp = new Response<>();
		String data = orderService.addOrder(rq.getRequestPayload());
		if (ApiResponse.SUCCESS.getCode().equals(data)) {
			resp.setStatusResponse(ApiResponse.valueOf(data));
		}else {
			resp.setStatusResponse(ApiResponse.SUCCESS);
		}
		return resp;
	}
}
