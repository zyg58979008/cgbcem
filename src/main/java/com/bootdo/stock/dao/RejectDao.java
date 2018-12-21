package com.bootdo.stock.dao;

import com.bootdo.stock.domain.RejectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 15:32:04
 */
@Mapper
public interface RejectDao {

	RejectDO get(String id);
	
	List<RejectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RejectDO reject);
	
	int update(RejectDO reject);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
