package com.itasoft.bookstore.beans;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class OrderDTO {

	@JsonProperty(value = "id_order")
	private String idOrder;
	
	@JsonProperty(value = "trx_date")
	private Date trxDate;
	
	@JsonProperty(value = "proses_by")
	private String prosesBy;
	
	@JsonProperty(value ="order")
	private Set<DetailOrderDTO> orderDetail;
	
}
