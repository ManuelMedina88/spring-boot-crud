package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IComputerDao;
import com.bolsadeideas.springboot.app.models.entity.Computer;

@Service
public class ComputerServiceImpl implements IComputerService {

	@Autowired
	private IComputerDao computerDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Computer> findAll() {
		// TODO Auto-generated method stub
		return (List<Computer>)computerDao.findAll();
	}

	@Override
	@Transactional
	public void save(Computer computer) {
		// TODO Auto-generated method stub
		computerDao.save(computer);
		
	}

	@Override
	public Computer findById(Long id) {
		// TODO Auto-generated method stub
		return computerDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		computerDao.deleteById(id);
		
	}

}
