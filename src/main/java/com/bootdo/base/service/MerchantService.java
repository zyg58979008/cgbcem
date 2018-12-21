package com.bootdo.base.service;

import com.bootdo.base.domain.MerchantDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface MerchantService {

	MerchantDO get(String id);
	
	List<MerchantDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MerchantDO merchant);
	
	int update(MerchantDO merchant);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int batchInsert(List<MerchantDO> merchantDOList);

	int synchronization(String orderId);

	void duplicate(String orderId);

	void removeCopy(String orderId);
}
