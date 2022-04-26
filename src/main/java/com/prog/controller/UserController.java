package com.prog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prog.entity.Login;
import com.prog.entity.User;
import com.prog.service.*;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class UserController {

	@Autowired 
	private UserService service;
	private LoginService lservice;
	
	@GetMapping("/")
	public String home(Model m)
	{
		LoginController v = new LoginController();
		return "index";
	}
	
	@GetMapping("/signup")
	public String addUser()
	{
		return "signup";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User u,HttpSession session)
	{
		System.out.println(u);
		User check = service.getUser(u.getUsername());
		System.out.println(check);
		System.out.println(Objects.nonNull(check));
		if(Objects.nonNull(check))
	     {
			session.setAttribute("msg", "Username already exists");
	    	return "redirect:/signup";
			
	     } else {
	    	 service.addUser(u);
			 session.setAttribute("msg", "User has been Registered");
			 return "redirect:/login";
	     }
	}

}
