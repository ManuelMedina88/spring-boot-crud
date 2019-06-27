package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.entity.Computer;
import com.bolsadeideas.springboot.app.models.service.IComputerService;

@Controller
@RequestMapping("/computer")
@SessionAttributes("computer")
public class MainController {
	
	@Autowired
	private IComputerService computerService;
	
	@GetMapping("/list")
	public String showList(Model model) {
		
		model.addAttribute("title","List of available computers");
		model.addAttribute("computers", computerService.findAll());
		
		return "list";
	}
	
	@GetMapping("/form")
	public String formComputer(Model model) {
		
		Computer computer = new Computer();
		model.addAttribute("title", "Register Computer");
		model.addAttribute("computer", computer);
		
		return "form";
	}
	
	@GetMapping("/form/{id}")
	public String editCumputer(@PathVariable(name="id") Long id,  Model model) {
		
		model.addAttribute("title", "Editing Computer");
		model.addAttribute("computer", computerService.findById(id));
		
		return "form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCumputer(@PathVariable(name="id") Long id,  Model model) {
		
		if(id > 0 && id != null)
			computerService.deleteById(id);
		
		return "redirect:/computer/list";
	}
	
	@PostMapping("/form")
	public String saveForm(@Valid @ModelAttribute("computer")Computer computer1,
			BindingResult result, SessionStatus status, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("title", "Register Computer");
			return "/form";
		}
		
		
		computerService.save(computer1);
		status.isComplete();
		return "redirect:/computer/list";
	}
	
	

}
