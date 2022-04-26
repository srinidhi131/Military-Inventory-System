package com.prog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prog.entity.Item;
import com.prog.repository.InventoryRepo;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepo repo;

	public void addItem(Item i) {
		repo.save(i);
	}

	public List<Item> getAllItem() {
		return repo.findAll();
	}

	public Item getItemById(int id) {
		Optional<Item> i = repo.findById(id);
		if (i.isPresent()) {
			return i.get();
		}
		return null;
	}

	public void deleteItem(int id) {
		repo.deleteById(id);
	}

	public Page<Item> getItemByPaginate(int currentPage, int size) {
		Pageable p = PageRequest.of(currentPage, size);
		return repo.findAll(p);
	}
	
	
}