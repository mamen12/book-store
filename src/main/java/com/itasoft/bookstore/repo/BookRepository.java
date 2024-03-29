package com.itasoft.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itasoft.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	@Query("SELECT book FROM Book book WHERE book.author=?1 AND book.name=?2")
	public Book getBookByAuthorAndName(String author, String name);
	
	
	@Modifying
	@Query("UPDATE Book p SET p.isDel=?1, p.idBook=?2")
	public void deleteBook(Integer isDell, String idBook);
}
