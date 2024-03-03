package com.itasoft.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.itasoft.bookstore.beans.BookDTO;
import com.itasoft.bookstore.entity.Book;
import com.itasoft.bookstore.repo.BookRepository;
import com.itasoft.bookstore.service.IBookService;

import io.micrometer.common.util.StringUtils;

@Service
public class BookServiceImpl implements IBookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepo;

	@Override
	public String saveAndUpdateBook(BookDTO dto) {
		String rs = "";
		try {
			
			if (!StringUtils.isEmpty(dto.getId())) {
				// update
				Book book = bookRepo.findById(dto.getId()).orElseThrow();
				Book bookUpdate = Book.builder()
						.idBook(book.getIdBook())
						.qty(book.getQty())
						.author(dto.getAuthor())
						.name(dto.getName())
						.price(dto.getPrice())
						.updatedAt(new Date())
						.createdAt(book.getCreatedAt())
						.isDel(0)
						.build();
				
				bookRepo.save(bookUpdate);
			}else {
				Book check = bookRepo.getBookByAuthorAndName(dto.getAuthor().toUpperCase(), dto.getName());
				if (ObjectUtils.isEmpty(check)) {
				Book bookSave = Book.builder()
					.idBook(UUID.randomUUID().toString())
					.author(dto.getAuthor().toUpperCase())
					.name(dto.getName())
					.price(dto.getPrice())
					.createdAt(new Date())
					.qty(dto.getQty())
					.isDel(0)
					.build();
					
					bookRepo.save(bookSave);
				}else {
					rs = "RECORD_ALREADY_EXISTS";
				}
			}
			rs = "SUCCESS";
		} catch (Exception e) {
			logger.error(e.getMessage());
			rs = "FAILED";
		}
		return rs;
	}

	@Override
	public List<BookDTO> listBook() {
		List<BookDTO> listBook = new ArrayList<>();
		List<Book> books = bookRepo.findAll();
		listBook = books.stream().map(b -> new BookDTO()
				.builder()
				.id(b.getIdBook())
				.author(b.getAuthor())
				.name(b.getName())
				.price(b.getPrice())
				.qty(b.getQty())
				.build()).collect(Collectors.toList());
		return listBook;
	}

	@Override
	public String deleteBook(BookDTO dto) {
		String rs = "";
		try {
			Book book = bookRepo.findById(dto.getId()).orElseThrow();
			if (!ObjectUtils.isEmpty(book)) {
				bookRepo.deleteBook(1, dto.getId());
				rs =  "SUCCESS";
			}else {
				rs =  "FAILED";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			rs = "DATA_NOT_FOUND";
		}
		return rs;
	}

	@Override
	public String updateQtyBook(BookDTO dto) {
		String rs = null;
		try {
			Book check = bookRepo.findById(dto.getId()).orElseThrow();
			Book book = Book.builder()
					.idBook(check.getIdBook())
					.qty(check.getQty() - dto.getQty())
					.author(check.getAuthor())
					.name(check.getName())
					.price(check.getPrice())
					.updatedAt(new Date())
					.createdAt(check.getCreatedAt())
					.isDel(0)
					.build();
			
			bookRepo.save(book);
		} catch (Exception e) {
			logger.error(e.getMessage());
			rs = "gagal";
		}
		return rs;
	}
	
	
}
