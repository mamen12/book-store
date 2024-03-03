package com.itasoft.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itasoft.bookstore.beans.BookDTO;
import com.itasoft.bookstore.beans.Request;
import com.itasoft.bookstore.beans.Response;
import com.itasoft.bookstore.constant.ApiResponse;
import com.itasoft.bookstore.service.IBookService;




@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private IBookService bookService;
	
	
	@RequestMapping(path = "/book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Object> bookSaveAndUpdate(@RequestBody Request<BookDTO> rq){
		Response<Object> resp = new Response<>();
		String data = bookService.saveAndUpdateBook(rq.getRequestPayload());
		if (ApiResponse.SUCCESS.getCode().equals(data)) {
			resp.setStatusResponse(ApiResponse.valueOf(data));
		}else {
			resp.setStatusResponse(ApiResponse.valueOf(data));
		}
		
		return resp;
	}
	
	@RequestMapping(path = "/books", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<BookDTO>> getAllBook(@RequestBody Request<BookDTO> rq){
		Response<List<BookDTO>> resp = new Response<>();
		List<BookDTO> data = bookService.listBook();
		if (data.size() > 0) {
			resp.setStatusResponse(ApiResponse.SUCCESS);
			resp.setData(data);
		}else {
			resp.setStatusResponse(ApiResponse.FAILED);
		}
		
		return resp;
	}
	
	@RequestMapping(path = "/book/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Object> bookDelete(@RequestBody Request<BookDTO> rq){
		Response<Object> resp = new Response<>();
		String data = bookService.saveAndUpdateBook(rq.getRequestPayload());
		if (ApiResponse.SUCCESS.getCode().equals(data)) {
			resp.setStatusResponse(ApiResponse.valueOf(data));
		}else {
			resp.setStatusResponse(ApiResponse.valueOf(data));
		}
		
		return resp;
	}	
}
