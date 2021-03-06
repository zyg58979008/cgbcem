package com.bootdo.system.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();

	Tree<DeptDO> getTree1();
	
	boolean checkDeptHasUser(Long deptId);

	Tree<DeptDO> treeByAreaLevel();

	Tree<DeptDO> treeViewByLevelForFirstOnline();

	int updateParentId(Long deptId);

	String[] getDeptIds(Long id);

	Tree<DeptDO> getTree(Long userId);

	Tree<DeptDO> treeByUserId();
}
