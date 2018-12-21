package com.bootdo.wuye.service.impl;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.wuye.dao.FangbenManageDao;
import com.bootdo.wuye.domain.FangbenDO;
import com.bootdo.wuye.service.FangbenManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class FangbenManageServiceImpl implements FangbenManageService {

	@Autowired
	private FangbenManageDao fangbenManageDao;

	@Override
	public FangbenDO get(Long id){
		return fangbenManageDao.get(id);
	}

	@Override
	public List<FangbenDO> list(Map<String, Object> map){
		return fangbenManageDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return fangbenManageDao.count(map);
	}

	@Override
	public int save(FangbenDO room){
		return fangbenManageDao.save(room);
	}

	@Override
	public int update(FangbenDO room){
		return fangbenManageDao.update(room);
	}

	@Override
	public int remove(Long id){
		return fangbenManageDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return fangbenManageDao.batchRemove(ids);
	}

	@Override
	public void removeNotPay(FangbenDO fangbenDO) {
		fangbenManageDao.removeNotPay(fangbenDO);
	}

	@Override
	public List<FangbenDO> listPay(Query query) {
		return fangbenManageDao.listPay(query);
	}

	@Override
	public int countPay(Query query) {
		return fangbenManageDao.countPay(query);
	}

	@Override
	public List<PayListDO> pay(Query query) {
		return fangbenManageDao.pay(query);
	}

	@Override
	public int countPayList(Query query) {
		return fangbenManageDao.countPayList(query);
	}
}
