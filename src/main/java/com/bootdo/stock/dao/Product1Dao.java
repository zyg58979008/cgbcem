package com.bootdo.stock.dao;

import com.bootdo.stock.domain.Product1DO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 09:10:32
 */
@Mapper
public interface Product1Dao {

	Product1DO get(Long id);
	
	List<Product1DO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Product1DO product1);
	
	int update(Product1DO product1);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
