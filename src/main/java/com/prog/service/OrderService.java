package com.prog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.entity.Order;
import com.prog.entity.User;
import com.prog.repository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;
	
	public void addOrder(Order i) {
		repo.save(i);
	}
	public List<Order> getAllOrders(String username) {
		return repo.findByUsername(username);
	}
	public void deleteOrder(int id) {
		repo.deleteById(id);
	}
	public Order getTaskById(int id) {
		Optional<Order> t = repo.findById(id);
		if (t.isPresent()) {
			return t.get();
		}
		return null;
	}
}
