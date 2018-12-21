package com.bootdo.base.dao;

import com.bootdo.base.domain.MerchantDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MerchantDao {

	MerchantDO get(String id);
	
	List<MerchantDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MerchantDO merchant);
	
	int update(MerchantDO merchant);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int batchInsert(@Param("merchantDOList") List<MerchantDO> merchantDOList);

	int synchronization(@Param("orderId") String orderId);

	void duplicate(@Param("orderId") String orderId);

	void removeCopy(@Param("orderId") String orderId);
}
