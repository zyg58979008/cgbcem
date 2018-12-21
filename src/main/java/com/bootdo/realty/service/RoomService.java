package com.bootdo.realty.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
public interface RoomService {
	
	RoomDO get(Long id);
	
	List<RoomDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomDO room);
	
	int update(RoomDO room);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int checkCode(RoomDO room);

	int batchInsert(List<RoomDO> roomDOList);

	int synchronization(String orderId);

	void duplicate(String orderId);

    void removeCopy(String orderId);

    List<DictDO> listByType(Map<String, Object> map);

    List<RoomDO> listNotHas(Query query);

	int countListNotHas(Query query);

    void updateArea(String orderId);

    List<RoomContractDO> getLoukuan(Long deptId);

    void updateRoom(Long roomId);

    String getSum(Map<String, Object> params);
}
