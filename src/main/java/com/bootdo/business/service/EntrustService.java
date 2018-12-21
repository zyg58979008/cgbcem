package com.bootdo.business.service;

import com.bootdo.business.domain.EntrustDO;
import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-27 15:53:01
 */
public interface EntrustService {
	
	EntrustDO get(Long id);
	
	List<EntrustDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EntrustDO entrust);
	
	int update(EntrustDO entrust);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<String> getEntrustList(Long deptId);

    int batchInsert(List<EntrustDO> entrustDOs);

	int batchInsertLeaseback(List<LeasebackDO> leasebackDOs);

    List<LeasebackDO> detailList(Query query);

	int detailCount(Query query);

    LeasebackDO getLease(Long id);

	String getId(String id, Long deptId);

    int savePay(PayListDO payListDO);

	void updateDetail(LeasebackDO leasebackDO);

	PayListDO getPay(Long id);

	void updatePay(PayListDO payListDO);

    List<PayListDO> pay(Query query);

	int countPayList(Query query);
}
