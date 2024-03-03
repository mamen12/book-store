package com.itasoft.bookstore.service;

import java.util.List;

import com.itasoft.bookstore.beans.DetailOrderDTO;

public interface IOrderDetailService {

	public String saveAllOrderDetail(List<DetailOrderDTO> listDetailOrder);
	
}
