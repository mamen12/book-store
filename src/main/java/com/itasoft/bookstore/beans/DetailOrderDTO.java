package com.itasoft.bookstore.beans;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DetailOrderDTO {

	@JsonProperty(value = "id_order_detail")
	private String idOrderDetail;
	
	@JsonProperty(value = "book_id")
	private String bookId;
	
	@JsonProperty(value ="book_name")
	private String bookName;
	
	@JsonProperty(value = "qty_order")
	private Integer qty;
	
	@JsonProperty(value = "sum_price")
	private BigDecimal sumPrice;
	
}
