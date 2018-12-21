package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.QueryDao;
import com.bootdo.stock.domain.QueryDO;
import com.bootdo.stock.service.QueryService;



@Service
public class QueryServiceImpl implements QueryService {
	@Autowired
	private QueryDao queryDao;
	
	@Override
	public QueryDO get(String id){
		return queryDao.get(id);
	}
	
	@Override
	public List<QueryDO> list(Map<String, Object> map){
		return queryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return queryDao.count(map);
	}
	
	@Override
	public int save(QueryDO query){
		return queryDao.save(query);
	}
	
	@Override
	public int update(QueryDO query){
		return queryDao.update(query);
	}
	
	@Override
	public int remove(String id){
		return queryDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return queryDao.batchRemove(ids);
	}
	
}
