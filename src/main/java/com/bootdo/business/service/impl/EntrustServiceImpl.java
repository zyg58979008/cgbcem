package com.bootdo.business.service.impl;

import com.bootdo.business.dao.EntrustDao;
import com.bootdo.business.domain.EntrustDO;
import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.business.service.EntrustService;
import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EntrustServiceImpl implements EntrustService {
	@Autowired
	private EntrustDao entrustDao;
	
	@Override
	public EntrustDO get(Long id){
		return entrustDao.get(id);
	}
	
	@Override
	public List<EntrustDO> list(Map<String, Object> map){
		return entrustDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return entrustDao.count(map);
	}
	
	@Override
	public int save(EntrustDO entrust){
		return entrustDao.save(entrust);
	}
	
	@Override
	public int update(EntrustDO entrust){
		return entrustDao.update(entrust);
	}
	
	@Override
	public int remove(Long id){
		EntrustDO entrustDO=entrustDao.get(id);
		entrustDao.removeLeaseback(entrustDO.getRoomId());
		return entrustDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return entrustDao.batchRemove(ids);
	}

	@Override
	public List<String> getEntrustList(Long deptId) {
		return entrustDao.getEntrustList(deptId);
	}

	@Override
	public int batchInsert(List<EntrustDO> entrustDOs) {
		return entrustDao.batchInsert(entrustDOs);
	}

	@Override
	public int batchInsertLeaseback(List<LeasebackDO> leasebackDOs) {
		return entrustDao.batchInsertLeaseback(leasebackDOs);
	}

	@Override
	public List<LeasebackDO> detailList(Query query) {
		return entrustDao.detailList(query);
	}

	@Override
	public int detailCount(Query query) {
		return entrustDao.detailCount(query);
	}

	@Override
	public LeasebackDO getLease(Long id) {
		return entrustDao.getLease(id);
	}

	@Override
	public String getId(String id, Long deptId) {
		return entrustDao.getId(id,deptId);
	}

	@Override
	public int savePay(PayListDO payListDO) {
		return entrustDao.savePay(payListDO);
	}

	@Override
	public void updateDetail(LeasebackDO leasebackDO) {
		entrustDao.updateDetail(leasebackDO);
	}

	@Override
	public PayListDO getPay(Long id) {
		return entrustDao.getPay(id);
	}

	@Override
	public void updatePay(PayListDO payListDO) {
		entrustDao.updatePay(payListDO);
	}

	@Override
	public List<PayListDO> pay(Query query) {
		return entrustDao.pay(query);
	}

	@Override
	public int countPayList(Query query) {
		return entrustDao.countPayList(query);
	}

}
