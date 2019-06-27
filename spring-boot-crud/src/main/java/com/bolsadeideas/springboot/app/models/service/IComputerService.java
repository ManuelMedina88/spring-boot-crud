package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Computer;

public interface IComputerService {
	
	public abstract List<Computer> findAll();
	
	public abstract void save(Computer computer);
	
	public abstract Computer findById(Long id);
	
	public abstract void deleteById(Long id);
	
}
