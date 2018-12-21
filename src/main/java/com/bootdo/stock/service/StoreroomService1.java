package com.bootdo.stock.service;

import com.bootdo.stock.domain.StoreroomDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-02 16:32:30
 */
public interface StoreroomService1 {
	
	StoreroomDO get(Long id);
	
	List<StoreroomDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StoreroomDO storeroom);
	
	int update(StoreroomDO storeroom);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
