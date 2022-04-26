package com.prog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.prog.entity.Item;
import com.prog.service.InventoryService;

@Controller
public class InventoryController {
	
	@Autowired
	private InventoryService service;

	@GetMapping("/Admin")
	public String home(Model m) {
		return findPaginated(0, m);

	}

	@GetMapping("/additem")
	public String addItemForm() {
		return "add_item";
	}

	@PostMapping("/registeritem")
	public String itemRegister(@ModelAttribute Item i, HttpSession session) {
		service.addItem(i);
		session.setAttribute("msg", "Item Added Sucessfully.");
		return "redirect:/Admin";
	}

	@GetMapping("/edititem/{id}")
	public String edit(@PathVariable int id, Model m) {
		Item i = service.getItemById(id);
		m.addAttribute("item", i);
		return "edit";
	}

	@PostMapping("/updateitem")
	public String updateItem(@ModelAttribute Item i, HttpSession session) {
		service.addItem(i);
		session.setAttribute("msg", "Item Data Update Sucessfully.");
		return "redirect:/Admin";
	}

	@GetMapping("/deleteitem/{id}")
	public String deleteItem(@PathVariable int id, HttpSession session) {

		service.deleteItem(id);
		session.setAttribute("msg", "Item Data Deleted Sucessfully.");
		return "redirect:/Admin";
	}

	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<Item> itemlist = service.getItemByPaginate(pageno, 7);
		m.addAttribute("item", itemlist);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", itemlist.getTotalPages());
		m.addAttribute("totalItem", itemlist.getTotalElements());
		return "index";
	}
}