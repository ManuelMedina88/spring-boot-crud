package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Computer;

@Repository
public interface IComputerDao extends PagingAndSortingRepository<Computer, Long>{

}
