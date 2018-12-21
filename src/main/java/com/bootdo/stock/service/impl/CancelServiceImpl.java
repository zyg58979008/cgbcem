package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.CancelDao;
import com.bootdo.stock.domain.CancelDO;
import com.bootdo.stock.service.CancelService;



@Service
public class CancelServiceImpl implements CancelService {
	@Autowired
	private CancelDao cancelDao;
	
	@Override
	public CancelDO get(String id){
		return cancelDao.get(id);
	}
	
	@Override
	public List<CancelDO> list(Map<String, Object> map){
		return cancelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return cancelDao.count(map);
	}
	
	@Override
	public int save(CancelDO cancel){
		return cancelDao.save(cancel);
	}
	
	@Override
	public int update(CancelDO cancel){
		return cancelDao.update(cancel);
	}
	
	@Override
	public int remove(String id){
		return cancelDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return cancelDao.batchRemove(ids);
	}
	
}
