package com.bootdo.business.service;

import com.bootdo.business.domain.BusinessBuildingDO;
import com.bootdo.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 *
 */
public interface BusinessBuildingService {
	
	BusinessBuildingDO get(Long id);
	
	List<BusinessBuildingDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BusinessBuildingDO building);
	
	int update(BusinessBuildingDO building);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	Tree<BusinessBuildingDO> getTree(Long type);

	boolean checkBuildingHasRoom(Long id);

	int updateParentId(Long id);
}
