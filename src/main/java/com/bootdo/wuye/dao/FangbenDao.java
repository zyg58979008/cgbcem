package com.bootdo.wuye.dao;


import com.bootdo.realty.domain.RoomDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
@Mapper
public interface FangbenDao {

	int update(RoomDO room);
}
