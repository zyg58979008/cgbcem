package com.bootdo.system.dao;

import com.bootdo.system.domain.AreaDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 巡检区域管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:07:35
 */
@Mapper
public interface AreaDao {

	AreaDO get(Long areaId);

	List<AreaDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(AreaDO area);

	int update(AreaDO area);

	int remove( Long areaId);

	int batchRemove(Long[] areaIds);

	Long[] listParentArea();

	int getAreaUserNumber( Long areaId);
}
