package com.bootdo.stock.dao;

import com.bootdo.stock.domain.ClassDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-30 14:49:38
 */
@Mapper
public interface ClassDao {

	ClassDO get(String id);
	
	List<ClassDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ClassDO classDO);
	
	int update(ClassDO classDO);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    String getId();
}
