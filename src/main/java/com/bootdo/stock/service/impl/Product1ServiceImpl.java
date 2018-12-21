package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.Product1Dao;
import com.bootdo.stock.domain.Product1DO;
import com.bootdo.stock.service.Product1Service;



@Service
public class Product1ServiceImpl implements Product1Service {
	@Autowired
	private Product1Dao product1Dao;
	
	@Override
	public Product1DO get(Long id){
		return product1Dao.get(id);
	}
	
	@Override
	public List<Product1DO> list(Map<String, Object> map){
		return product1Dao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return product1Dao.count(map);
	}
	
	@Override
	public int save(Product1DO product1){
		return product1Dao.save(product1);
	}
	
	@Override
	public int update(Product1DO product1){
		return product1Dao.update(product1);
	}
	
	@Override
	public int remove(Long id){
		return product1Dao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return product1Dao.batchRemove(ids);
	}
	
}
