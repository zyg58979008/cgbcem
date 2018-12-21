package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.TotalDao;
import com.bootdo.stock.domain.TotalDO;
import com.bootdo.stock.service.TotalService;



@Service
public class TotalServiceImpl implements TotalService {
	@Autowired
	private TotalDao totalDao;
	
	@Override
	public TotalDO get(Long id){
		return totalDao.get(id);
	}
	
	@Override
	public List<TotalDO> list(Map<String, Object> map){
		return totalDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return totalDao.count(map);
	}
	
	@Override
	public int save(TotalDO total){
		return totalDao.save(total);
	}
	
	@Override
	public int update(TotalDO total){
		return totalDao.update(total);
	}
	
	@Override
	public int remove(Long id){
		return totalDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return totalDao.batchRemove(ids);
	}
	
}
