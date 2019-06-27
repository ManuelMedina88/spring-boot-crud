package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Computer;

@Repository
public interface IComputerDao extends CrudRepository<Computer, Long>{

}
