package com.bootdo.system.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.AreaDO;

import java.util.List;
import java.util.Map;

/**
 * 巡检区域管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:07:35
 */
public interface AreaService {

	AreaDO get(Long deptId);

	List<AreaDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(AreaDO sysDept);

	int update(AreaDO sysDept);

	int remove(Long deptId);

	int batchRemove(Long[] deptIds);

	Tree<AreaDO> getTree();

	boolean checkAreaHasUser(Long deptId);
}
