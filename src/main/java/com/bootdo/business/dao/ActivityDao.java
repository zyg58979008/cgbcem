package com.bootdo.business.dao;

import com.bootdo.business.domain.ActivityDO;

import java.util.List;
import java.util.Map;

import com.bootdo.business.domain.ActivityGeneralDO;
import com.bootdo.business.domain.ActivityPayListDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 活动管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-09 15:52:38
 */
@Mapper
public interface ActivityDao {

	ActivityDO getDetail(Long id);

	ActivityDO getTotal(Map<String, Object> map);

	ActivityDO get(Long id);
	
	List<ActivityDO> listDetail(Map<String, Object> map);

	List<String> getShop(String orderId);

	ActivityGeneralDO getGeneral(Long id);

	List<ActivityGeneralDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int countDetail(Map<String, Object> map);

	int save(ActivityDO activity);

	int saveList(ActivityPayListDO activityPayListDO);

	int savenew(ActivityDO activity);

	int update(ActivityDO activity);

	int updateGeneral(ActivityGeneralDO activityGeneralDO);

	int updateDetail(ActivityDO activityDO);

	int remove(Long id);

	int deleteGeneral(Long id);

	int removeDetail(Long id);

	int removeDetailPayList(Long id);

	int batchRemove(Long[] ids);

	int deletePayList(Long[] ids);

	int deleteDetail(String orderId);

	int batchInsert(@Param("activityDOList") List<ActivityDO> activityDOList);

	void saveGeneral(ActivityGeneralDO activityGeneralDO);

	String getId(@Param("s")String s,@Param("deptId") Long deptId);

	Long[] getDetailIds(String orderId);
}
