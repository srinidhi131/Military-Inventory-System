package com.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.entity.Login;
import com.prog.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository repo;
	  
	  public Login login(String username, String password) {
	  Login user = repo.findByUsernameAndPassword(username, password);
	   return user;
	  }
	
}
