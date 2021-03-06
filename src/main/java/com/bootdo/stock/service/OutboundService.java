package com.bootdo.stock.service;

import com.bootdo.stock.domain.OutboundDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-02 16:32:30
 */
public interface OutboundService {
	
	OutboundDO get(String id);
	
	List<OutboundDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OutboundDO outbound);
	
	int update(OutboundDO outbound);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
