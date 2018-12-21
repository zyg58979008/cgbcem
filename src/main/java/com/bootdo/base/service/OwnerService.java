package com.bootdo.base.service;

import com.bootdo.base.domain.OwnerDO;
import com.bootdo.realty.domain.RoomDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface OwnerService {

	OwnerDO get(String id);
	
	List<OwnerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OwnerDO owner);
	
	int update(OwnerDO owner);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int batchInsert(List<OwnerDO> ownerDOList);

	int synchronization(String orderId);

	void duplicate(String orderId);

	void removeCopy(String orderId);
}
