package com.itasoft.bookstore.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="tbl_book")
public class Book {

	@Id
	@Column(name = "id_book")
	private String idBook;
	
	@Column(name = "name_book")
	private String name;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "price_book")
	private BigDecimal price;
	
	@Column(name = "qty_book")
	private Integer qty;
	
	@Column(name = "created_at")
    private Date createdAt;
    
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @Column(name = "id_del")
    private Integer isDel;
}
