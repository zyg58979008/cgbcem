package com.bootdo.base.dao;

import com.bootdo.base.domain.OwnerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-25 13:28:58
 */
@Mapper
public interface OwnerDao {

	OwnerDO get(String id);
	
	List<OwnerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OwnerDO owner);
	
	int update(OwnerDO owner);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int batchInsert(@Param("ownerDOList") List<OwnerDO> ownerDOList);

	int synchronization(@Param("orderId") String orderId);

	void duplicate(@Param("orderId") String orderId);

	void removeCopy(@Param("orderId") String orderId);
}
