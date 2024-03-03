package com.itasoft.bookstore.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "tbl_order")
public class Order {
	
	@Id
	@Column(name = "id_order")
	private String idOrder;

	@Column(name = "trx_date")
	private Date trxDate;
	
	@Column(name= "proses_by")
	private String prosesBy;
	
}

