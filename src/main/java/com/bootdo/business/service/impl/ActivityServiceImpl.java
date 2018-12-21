package com.bootdo.business.service.impl;

import com.bootdo.business.domain.ActivityGeneralDO;
import com.bootdo.business.domain.ActivityPayListDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.business.dao.ActivityDao;
import com.bootdo.business.domain.ActivityDO;
import com.bootdo.business.service.ActivityService;



@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityDao activityDao;

	@Override
	public ActivityDO get(Long id){
		return activityDao.get(id);
	}

	@Override
	public ActivityDO getDetail(Long id){
		return activityDao.getDetail(id);
	}

	@Override
	public ActivityDO getTotal(Map<String, Object> map){
		return activityDao.getTotal(map);
	}

	@Override
	public ActivityGeneralDO getGeneral(Long id){
		return activityDao.getGeneral(id);
	}

	@Override
	public List<ActivityDO> listDetail(Map<String, Object> map){
		return activityDao.listDetail(map);
	}

	@Override
	public List<String> getShop(String orderId){
		return activityDao.getShop(orderId);
	}

	@Override
	public List<ActivityGeneralDO> list(Map<String, Object> map){
		return activityDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return activityDao.count(map);
	}

	@Override
	public int countDetail(Map<String, Object> map){
		return activityDao.countDetail(map);
	}

	@Override
	public int save(ActivityDO activity){
		return activityDao.save(activity);
	}
	@Override
	public int savenew(ActivityDO activity){
		activity.setUnreceived(activity.getDmDan()+activity.getCardMoney()+activity.getActivityMoney());
		activity.setCardMoneyReceived(0.00);
		activity.setDmDanReceived(0.00);
		activity.setActivityMoneyReceived(0.00);
		activity.setCardMoneyUnreceived(activity.getCardMoney());
		activity.setDmDanUnreceived(activity.getDmDan());
		activity.setActivityMoneyUnreceived(activity.getActivityMoney());
		activity.preInsert();
		return activityDao.savenew(activity);
	}
	public int saveList(ActivityPayListDO activityPayListDO){
		return activityDao.saveList(activityPayListDO);
	}
	@Override
	public int update(ActivityDO activity){
		return activityDao.update(activity);
	}

	@Override
	public int updateGeneral(ActivityGeneralDO activityGeneralDO){
		return activityDao.updateGeneral(activityGeneralDO);
	}

	@Override
	public int updateDetail(ActivityDO activityDO){
		return activityDao.updateDetail(activityDO);
	}

	@Override
	public int remove(Long id){
		return activityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return activityDao.batchRemove(ids);
	}

	@Override
	public int batchInsert(List<ActivityDO> activityDOList) {
		return activityDao.batchInsert(activityDOList);
	}

	@Override
	public void saveGeneral(ActivityGeneralDO activityGeneralDO) {
		 activityDao.saveGeneral(activityGeneralDO);
	}
	@Override
	public String getId(String s, Long deptId){
		return activityDao.getId(s,deptId);
	}

	@Override
	public int removeGeneral(Long id){
		ActivityGeneralDO activityGeneralDO = new ActivityGeneralDO();
		Long[] ids = null;
		activityGeneralDO = activityDao.getGeneral(id);
		ids = activityDao.getDetailIds(activityGeneralDO.getOrderId());
		activityDao.deleteDetail(activityGeneralDO.getOrderId());
		activityDao.deletePayList(ids);
		return activityDao.deleteGeneral(id);
	}

	@Override
	public int removeDetail(Long id){
		activityDao.removeDetailPayList(id);
		return activityDao.removeDetail(id);
	}


}
