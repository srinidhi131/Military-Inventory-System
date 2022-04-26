package com.prog.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prog.entity.*;
import com.prog.service.*;

@Controller
public class LoginController {
	
	@Autowired
    private LoginService userService;
	@Autowired
	private UserService service;
	@Autowired 
	private OrderService oService;
	@Autowired 
	private ProductService pService;
	Login oauthUser;
	User dept;
                                  
    @GetMapping("/login")
          
    public ModelAndView login() {
     ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }
    
    @GetMapping("/User")
	public String userhome(Model m)
	{
    	List<Item> i = pService.getAllItems();
		m.addAttribute("product", i);
		return "userhome";
	}
    
    @GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
		Item q = pService.getItemById(id);
		m.addAttribute("qu", q);
		return "quantity";
	}
    
    @PostMapping("/update")
	public String updateEmp(@ModelAttribute Item qu, HttpSession session) {
		System.out.println(qu);
		Item match = pService.getItemById(qu.getId());
		System.out.println(qu.getQuantity() <= match.getQuantity());
		int d = match.getQuantity() - qu.getQuantity();
		if(qu.getQuantity() <= match.getQuantity()) {
			System.out.println("Hello");
			Order or = new Order();
			or.setProductname(qu.getName());
			or.setQuantity(qu.getQuantity());
			or.setUsername(oauthUser.getUsername());
			oService.addOrder(or);
			match.setQuantity(d);
			pService.addItem(match);
		}
		else {
			String message = "Order Could not be Placed as available items are : " + match.getQuantity();
			session.setAttribute("msg", message);
		}
		return "redirect:/orders";
	}
    
    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user , HttpSession session) {
    
    oauthUser = userService.login(user.getUsername(), user.getPassword());
    dept = service.getdept(oauthUser.getUsername());
    System.out.println(dept);
     if(Objects.nonNull(oauthUser))
     {
    	 if(dept.getDepartment().equals("user")) {
    		 return "redirect:/User";
    	 }
    	 else if(dept.getDepartment().equals("admin")) {
    		 return "redirect:/Admin";
    	 }
    	 else{
    		 return "redirect:/Service";
    	 }
     } else {
    	 session.setAttribute("msg", "Incorrect Credentials");
    	 return "redirect:/login";
     }
 
}
    
    @GetMapping("/orders")
    public String home(Model m)
	{
		List<Order> o = oService.getAllOrders(oauthUser.getUsername());
		m.addAttribute("order", o);
		return "orders";
	}
    @GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable int id, HttpSession session) {

		oService.deleteOrder(id);
		session.setAttribute("msg", "Order Deleted");
		return "redirect:/orders";
	}
	
    
}
