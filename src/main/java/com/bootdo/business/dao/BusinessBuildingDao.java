package com.bootdo.business.dao;

import com.bootdo.business.domain.BusinessBuildingDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 */
@Mapper
public interface BusinessBuildingDao {

	BusinessBuildingDO get(Long id);
	
	List<BusinessBuildingDO> list(Map<String, Object> map);

	List<BusinessBuildingDO> listByLevel(BusinessBuildingDO BusinessBuildingDO);

	List<BusinessBuildingDO> getListForB(BusinessBuildingDO BusinessBuildingDO);

	int count(Map<String, Object> map);
	
	int save(BusinessBuildingDO building);
	
	int update(BusinessBuildingDO building);
	
	int remove(BusinessBuildingDO id);
	
	int batchRemove(Long[] ids);
	
	Long[] listParentbuilding();
	
	int checkBuildingHasRoom(Long id);

	int updateParentId(Long id);
}
