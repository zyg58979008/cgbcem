package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.RejectDao;
import com.bootdo.stock.domain.RejectDO;
import com.bootdo.stock.service.RejectService;



@Service
public class RejectServiceImpl implements RejectService {
	@Autowired
	private RejectDao rejectDao;
	
	@Override
	public RejectDO get(String id){
		return rejectDao.get(id);
	}
	
	@Override
	public List<RejectDO> list(Map<String, Object> map){
		return rejectDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rejectDao.count(map);
	}
	
	@Override
	public int save(RejectDO reject){
		return rejectDao.save(reject);
	}
	
	@Override
	public int update(RejectDO reject){
		return rejectDao.update(reject);
	}
	
	@Override
	public int remove(String id){
		return rejectDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return rejectDao.batchRemove(ids);
	}
	
}
