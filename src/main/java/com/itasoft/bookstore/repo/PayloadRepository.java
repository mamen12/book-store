package com.itasoft.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itasoft.bookstore.entity.PayloadProperties;

@Repository
public interface PayloadRepository extends JpaRepository<PayloadProperties, String>{

}
