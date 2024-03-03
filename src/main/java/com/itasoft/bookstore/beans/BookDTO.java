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
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BookDTO {
	
	@JsonProperty(value = "id_book")
	private String id;
	
	@JsonProperty(value = "name_book")
	private String name;
	
	@JsonProperty(value ="author_book")
	private String author;
	
	@JsonProperty(value = "price_book")
	private BigDecimal price;
	
	@JsonProperty(value = "qty_book")
	private Integer qty;
	
}
