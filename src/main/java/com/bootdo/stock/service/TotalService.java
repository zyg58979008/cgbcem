package com.bootdo.stock.service;

import com.bootdo.stock.domain.TotalDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-11 16:14:54
 */
public interface TotalService {
	
	TotalDO get(Long id);
	
	List<TotalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TotalDO total);
	
	int update(TotalDO total);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
