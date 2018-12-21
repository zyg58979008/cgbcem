package com.bootdo.stock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.OutboundDao;
import com.bootdo.stock.domain.OutboundDO;
import com.bootdo.stock.service.OutboundService;



@Service
public class OutboundServiceImpl implements OutboundService {
	@Autowired
	private OutboundDao outboundDao;
	
	@Override
	public OutboundDO get(String id){
		return outboundDao.get(id);
	}
	
	@Override
	public List<OutboundDO> list(Map<String, Object> map){
		return outboundDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return outboundDao.count(map);
	}
	
	@Override
	public int save(OutboundDO outbound){
		return outboundDao.save(outbound);
	}
	
	@Override
	public int update(OutboundDO outbound){
		return outboundDao.update(outbound);
	}
	
	@Override
	public int remove(String id){
		return outboundDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return outboundDao.batchRemove(ids);
	}
	
}
