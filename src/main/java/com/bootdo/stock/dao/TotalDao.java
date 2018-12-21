package com.bootdo.stock.dao;

import com.bootdo.stock.domain.TotalDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-11 16:14:54
 */
@Mapper
public interface TotalDao {

	TotalDO get(Long id);
	
	List<TotalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TotalDO total);
	
	int update(TotalDO total);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
