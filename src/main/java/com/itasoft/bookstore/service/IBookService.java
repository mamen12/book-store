package com.itasoft.bookstore.service;

import java.util.List;

import com.itasoft.bookstore.beans.BookDTO;

public interface IBookService {
	
	public String saveAndUpdateBook(BookDTO book);
	public List<BookDTO> listBook();
	public String deleteBook(BookDTO book);
	public String updateQtyBook(BookDTO book);
}
