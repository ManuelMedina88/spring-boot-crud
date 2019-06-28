package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Computer;
import com.bolsadeideas.springboot.app.models.service.IComputerService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@RequestMapping("/computer")
@SessionAttributes("computer")
public class MainController {
	
	@Autowired
	private IComputerService computerService;
	
	@GetMapping("/list")
	public String showList(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		
		Page<Computer> computers = computerService.findAll(pageRequest);
		
		PageRender<Computer> pageRender = new PageRender<Computer>("/computer/list", computers);
		model.addAttribute("title","List of available computers");
		model.addAttribute("computers", computers);
		model.addAttribute("page", pageRender);
		
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
	public String editCumputer(@PathVariable(name="id") Long id, RedirectAttributes flash, Model model) {
		Computer computer =  computerService.findById(id);
		
		if(computer == null) {
			
			flash.addFlashAttribute("warning", "The computer requested doesn't exit!");
			return "redirect:/computer/list";
		}
		
		model.addAttribute("title", "Editing Computer");
		model.addAttribute("computer",computer);
		
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
			BindingResult result, RedirectAttributes flash, SessionStatus status, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("title", "Register Computer");
			return "/form";
		}
		
		
		computerService.save(computer1);
		flash.addFlashAttribute("success", "Computer has been successfully registered.");
		status.isComplete();
		
		return "redirect:/computer/list";
	}
	
	

}
