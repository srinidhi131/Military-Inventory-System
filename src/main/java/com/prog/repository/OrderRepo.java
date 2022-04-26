package com.prog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import com.prog.entity.Order;
import com.prog.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{
	List<Order> findByUsername(String username);
}
