package com.bootdo.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.business.dao.PayListDao;
import com.bootdo.business.domain.PayListDO;
import com.bootdo.business.service.PayListService;



@Service
public class PayListServiceImpl implements PayListService {
	@Autowired
	private PayListDao payListDao;
	
	@Override
	public PayListDO get(Long id){
		return payListDao.get(id);
	}

	@Override
	public PayListDO getForChechang(Long id){
		return payListDao.getForChechang(id);
	}

	@Override
	public List<PayListDO> list(Map<String, Object> map){
		return payListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payListDao.count(map);
	}
	
	@Override
	public int save(PayListDO payList){
		return payListDao.save(payList);
	}

	@Override
	public int saveForChechang(PayListDO payList){
		return payListDao.saveForChechang(payList);
	}

	@Override
	public int update(PayListDO payList){
		return payListDao.update(payList);
	}

	@Override
	public int updateForChechang(PayListDO payList){
		return payListDao.updateForChechang(payList);
	}

	@Override
	public int remove(Long id){
		return payListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return payListDao.batchRemove(ids);
	}
	
}
