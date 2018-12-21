package com.bootdo.realty.dao;

import com.bootdo.realty.domain.BuildingDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 */
@Mapper
public interface BuildingDao {

	BuildingDO get(Long id);
	
	List<BuildingDO> list(Map<String, Object> map);

	List<BuildingDO> listByLevel(BuildingDO BuildingDO);

	List<BuildingDO> getListForB(BuildingDO BuildingDO);

	int count(Map<String, Object> map);
	
	int save(BuildingDO building);
	
	int update(BuildingDO building);
	
	int remove(BuildingDO id);
	
	int batchRemove(Long[] ids);
	
	Long[] listParentbuilding();
	
	int checkBuildingHasRoom(Long id);

	int updateParentId(Long id);
}
