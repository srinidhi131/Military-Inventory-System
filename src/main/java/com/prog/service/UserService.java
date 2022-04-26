package com.prog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.entity.Login;
import com.prog.entity.User;
import com.prog.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	User dept;
	public void addUser(User u) {
		repo.save(u);
	}
	
	public User getdept (String username) {
		User dept = repo.findByUsername(username);
		   return dept;
	}
	
	public User getUser(String username) {
		User u = repo.findByUsername(username);
		return u;
	}
}
