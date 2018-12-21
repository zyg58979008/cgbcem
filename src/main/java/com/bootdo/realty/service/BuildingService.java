package com.bootdo.realty.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.realty.domain.BuildingDO;

import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 *
 */
public interface BuildingService {
	
	BuildingDO get(Long id);
	
	List<BuildingDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BuildingDO building);
	
	int update(BuildingDO building);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	Tree<BuildingDO> getTree(Long type);

	boolean checkBuildingHasRoom(Long id);

	int updateParentId(Long id);
}
