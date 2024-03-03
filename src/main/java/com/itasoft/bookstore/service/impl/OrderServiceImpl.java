package com.itasoft.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.itasoft.bookstore.beans.BookDTO;
import com.itasoft.bookstore.beans.DetailOrderDTO;
import com.itasoft.bookstore.beans.OrderDTO;
import com.itasoft.bookstore.entity.Order;
import com.itasoft.bookstore.entity.OrderDetail;
import com.itasoft.bookstore.repo.OrderDetailRepository;
import com.itasoft.bookstore.repo.OrderRepository;
import com.itasoft.bookstore.service.IBookService;
import com.itasoft.bookstore.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private IBookService bookService;
	
	@Override
	public String addOrder(OrderDTO dto) {
		String rs = "";
		try {
			if (!dto.getProsesBy().isEmpty()) {
				String uuidOrder = UUID.randomUUID().toString();
				List<OrderDetail> orderDetails = new ArrayList<>();  
				Order order = Order.builder()
						.idOrder(uuidOrder)
						.trxDate(new Date())
						.prosesBy(dto.getProsesBy())
						.build(); 
				
				for (DetailOrderDTO dtl : dto.getOrderDetail()) {
					orderDetails.add(
							OrderDetail.builder()
							.id(UUID.randomUUID().toString())
							.idOrder(uuidOrder)
							.idBook(dtl.getBookId())
							.bookName(dtl.getBookName())
							.qty(dtl.getQty())
							.sumPrice(dtl.getSumPrice())
							.build());
				}
				
				
				order = orderRepo.save(order);
				orderDetails =  orderDetailRepo.saveAll(orderDetails);
				
				if (!ObjectUtils.isEmpty(order) && orderDetails.size() > 0) {
					for (OrderDetail orderDetail : orderDetails) {
						BookDTO bookDto = BookDTO.builder()
								.id(orderDetail.getIdBook())
								.qty(orderDetail.getQty())
								.build();
						bookService.updateQtyBook(bookDto);
						
					}
				}
				
			}else {
				rs = "FAILED";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rs;
		
	}
	
}
