package com.bootdo.stock.service;

import com.bootdo.stock.domain.ProductDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-02 11:05:45
 */
public interface ProductService {
	
	ProductDO get(String id);
	
	List<ProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProductDO product);
	
	int update(ProductDO product);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
