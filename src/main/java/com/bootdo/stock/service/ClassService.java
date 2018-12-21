package com.bootdo.stock.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.stock.domain.ClassDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-30 14:49:38
 */
public interface ClassService {
	
	ClassDO get(String id);
	
	List<ClassDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ClassDO classDO);
	
	int update(ClassDO classDO);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	Tree<ClassDO> getTree();

	boolean checkStockHasClass(Long classId);

    String getId();
}
