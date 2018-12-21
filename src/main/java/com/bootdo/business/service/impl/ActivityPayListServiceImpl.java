package com.bootdo.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.business.dao.ActivityPayListDao;
import com.bootdo.business.domain.ActivityPayListDO;
import com.bootdo.business.service.ActivityPayListService;



@Service
public class ActivityPayListServiceImpl implements ActivityPayListService {
	@Autowired
	private ActivityPayListDao activityPayListDao;
	
	@Override
	public ActivityPayListDO get(Long id){
		return activityPayListDao.get(id);
	}
	
	@Override
	public List<ActivityPayListDO> list(Map<String, Object> map){
		return activityPayListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return activityPayListDao.count(map);
	}
	
	@Override
	public int save(ActivityPayListDO activityPayList){
		return activityPayListDao.save(activityPayList);
	}
	
	@Override
	public int update(ActivityPayListDO activityPayList){
		return activityPayListDao.update(activityPayList);
	}
	
	@Override
	public int remove(Long id){
		return activityPayListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return activityPayListDao.batchRemove(ids);
	}
	
}
