package com.bootdo.business.service;

import com.bootdo.business.domain.ActivityDO;
import com.bootdo.business.domain.ActivityGeneralDO;
import com.bootdo.business.domain.ActivityPayListDO;

import java.util.List;
import java.util.Map;

/**
 * 活动管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-09 15:52:38
 */
public interface ActivityService {
	
	ActivityDO get(Long id);

	ActivityDO getDetail(Long id);

	ActivityDO getTotal(Map<String, Object> map);

	ActivityGeneralDO getGeneral(Long id);
	
	List<ActivityDO> listDetail(Map<String, Object> map);

	List<ActivityGeneralDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int countDetail(Map<String, Object> map);

	int save(ActivityDO activity);

	int saveList(ActivityPayListDO activityPayListDO);

	int savenew(ActivityDO activity);

	List<String> getShop(String orderId);

	int update(ActivityDO activity);

	int updateGeneral(ActivityGeneralDO activityGeneralDO);

	int updateDetail(ActivityDO activityDO);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchInsert(List<ActivityDO> activityDOList);

	void saveGeneral(ActivityGeneralDO activityGeneralDO);

	String getId(String s, Long deptId);

	int removeGeneral(Long id);

	int removeDetail(Long id);
}
