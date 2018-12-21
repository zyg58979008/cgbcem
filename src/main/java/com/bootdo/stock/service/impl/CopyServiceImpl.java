package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.CopyDao;
import com.bootdo.stock.domain.CopyDO;
import com.bootdo.stock.service.CopyService;



@Service
public class CopyServiceImpl implements CopyService {
	@Autowired
	private CopyDao copyDao;
	
	@Override
	public CopyDO get(String id){
		return copyDao.get(id);
	}
	
	@Override
	public List<CopyDO> list(Map<String, Object> map){
		return copyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return copyDao.count(map);
	}
	
	@Override
	public int save(CopyDO copy){
		return copyDao.save(copy);
	}
	
	@Override
	public int update(CopyDO copy){
		return copyDao.update(copy);
	}
	
	@Override
	public int remove(String id){
		return copyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return copyDao.batchRemove(ids);
	}
	
}
