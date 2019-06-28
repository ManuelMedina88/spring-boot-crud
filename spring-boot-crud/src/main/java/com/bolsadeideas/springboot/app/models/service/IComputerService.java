package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Computer;

public interface IComputerService {
	
	public abstract List<Computer> findAll();
	
	public Page<Computer> findAll(Pageable page);
	
	public abstract void save(Computer computer);
	
	public abstract Computer findById(Long id);
	
	public abstract void deleteById(Long id);
	
}
