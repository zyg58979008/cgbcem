package com.bootdo.stock.service;

import com.bootdo.stock.domain.CancelDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 15:32:04
 */
public interface CancelService {
	
	CancelDO get(String id);
	
	List<CancelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CancelDO cancel);
	
	int update(CancelDO cancel);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
