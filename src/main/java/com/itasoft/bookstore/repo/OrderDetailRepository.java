package com.itasoft.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itasoft.bookstore.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

//	Optional<Set<OrderDetail>> findByOrder( order);
}
