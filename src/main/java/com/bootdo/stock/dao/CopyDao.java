package com.bootdo.stock.dao;

import com.bootdo.stock.domain.CopyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-10 17:14:31
 */
@Mapper
public interface CopyDao {

	CopyDO get(String id);
	
	List<CopyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CopyDO copy);
	
	int update(CopyDO copy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
