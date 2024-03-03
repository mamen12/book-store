package com.itasoft.bookstore.entity;

import java.math.BigDecimal;

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
@Table(name = "tbl_order_detail")
public class OrderDetail {

	@Id
	@Column(name = "id_order_detail")
	private String id;
	
	@Column(name = "id_book")
	private String idBook;
	
	@Column(name = "book_name")
	private String bookName;
	
	@Column(name = "qty")
	private Integer qty;
	
	@Column(name = "sum_price")
	private BigDecimal sumPrice;

	@Column(name = "id_order")
	private String idOrder;
}
