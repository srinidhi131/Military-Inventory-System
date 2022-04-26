package com.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog.entity.Item;

@Repository
public interface InventoryRepo extends JpaRepository<Item, Integer>{

}