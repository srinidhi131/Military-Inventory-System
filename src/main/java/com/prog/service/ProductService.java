package com.prog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.entity.Item;
import com.prog.repository.InventoryRepo;

@Service
public class ProductService {

	@Autowired
	private InventoryRepo repo;
	
	public List<Item> getAllItems() {
		return repo.findAll();
	}
	
	public Item getItemById(int id) {
		Optional<Item> t = repo.findById(id);
			return t.get();
	}
	
	public void addItem(Item t) {
		repo.save(t);
	}

}
