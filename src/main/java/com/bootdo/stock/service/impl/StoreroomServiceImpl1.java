package com.bootdo.stock.service.impl;

import com.bootdo.stock.dao.StoreroomDao1;
import com.bootdo.stock.service.StoreroomService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.domain.StoreroomDO;



@Service
public class StoreroomServiceImpl1 implements StoreroomService1 {
	@Autowired
	private StoreroomDao1 storeroomDao;
	
	@Override
	public StoreroomDO get(Long id){
		return storeroomDao.get(id);
	}
	
	@Override
	public List<StoreroomDO> list(Map<String, Object> map){
		return storeroomDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return storeroomDao.count(map);
	}
	
	@Override
	public int save(StoreroomDO storeroom){
		return storeroomDao.save(storeroom);
	}
	
	@Override
	public int update(StoreroomDO storeroom){
		return storeroomDao.update(storeroom);
	}
	
	@Override
	public int remove(Long id){
		return storeroomDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return storeroomDao.batchRemove(ids);
	}
	
}
