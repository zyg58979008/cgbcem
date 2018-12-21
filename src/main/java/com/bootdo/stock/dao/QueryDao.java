package com.bootdo.stock.dao;

import com.bootdo.stock.domain.QueryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-07 21:22:37
 */
@Mapper
public interface QueryDao {

	QueryDO get(String id);
	
	List<QueryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QueryDO query);
	
	int update(QueryDO query);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
