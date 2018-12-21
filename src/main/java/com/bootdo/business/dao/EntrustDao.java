package com.bootdo.business.dao;


import java.util.List;
import java.util.Map;

import com.bootdo.business.domain.EntrustDO;
import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-27 15:53:01
 */
@Mapper
public interface EntrustDao {

	EntrustDO get(Long id);
	
	List<EntrustDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EntrustDO entrust);
	
	int update(EntrustDO entrust);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<String> getEntrustList(@Param("deptId") Long deptId);

    int batchInsert(@Param("entrustDOs")List<EntrustDO> entrustDOs);

	int batchInsertLeaseback(@Param("leasebackDOs") List<LeasebackDO> leasebackDOs);

    void removeLeaseback(@Param("roomId") Long roomId);

    List<LeasebackDO> detailList(Query query);

	int detailCount(Query query);

    LeasebackDO getLease(Long id);

	String getId(@Param("id")String id,@Param("deptId") Long deptId);

	int savePay(PayListDO payListDO);

	void updateDetail(LeasebackDO leasebackDO);

	PayListDO getPay(@Param("id")Long id);

	void updatePay(PayListDO payListDO);

    List<PayListDO> pay(Query query);

	int countPayList(Query query);
}
