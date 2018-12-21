package com.bootdo.realty.dao;


import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
@Mapper
public interface RoomDao {

	RoomDO get(Long id);
	
	List<RoomDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomDO room);
	
	int update(RoomDO room);
	
	int remove(Long id);
	
	int batchRemove(@Param("ids") Long[] ids);

    int checkCode(RoomDO room);

	int batchInsert(@Param("roomDOList") List<RoomDO> roomDOList);

    int synchronization(@Param("orderId") String orderId);

    void duplicate(@Param("orderId") String orderId);

    void removeCopy(@Param("orderId") String orderId);

    List<DictDO> listByType(Map<String, Object> map);

    List<RoomDO> listNotHas(Query query);

	int countListNotHas(Query query);

    void updateArea(@Param("orderId") String orderId);

    List<RoomContractDO> getLoukuan(@Param("deptId") Long deptId);

    void updateRoom(@Param("roomId")Long roomId);

    String getSum(Map<String, Object> params);
}
