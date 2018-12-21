package com.bootdo.business.service;

import com.bootdo.business.domain.ActivityPayListDO;

import java.util.List;
import java.util.Map;

/**
 * 商铺合同缴费列表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-09 17:14:10
 */
public interface ActivityPayListService {
	
	ActivityPayListDO get(Long id);
	
	List<ActivityPayListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ActivityPayListDO activityPayList);
	
	int update(ActivityPayListDO activityPayList);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
