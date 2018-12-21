package com.bootdo.realty.service.impl;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.domain.RoomContractDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.realty.dao.RoomDao;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.realty.service.RoomService;



@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public RoomDO get(Long id){
		return roomDao.get(id);
	}
	
	@Override
	public List<RoomDO> list(Map<String, Object> map){
		return roomDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomDao.count(map);
	}
	
	@Override
	public int save(RoomDO room){
		room.preInsert();
		return roomDao.save(room);
	}
	
	@Override
	public int update(RoomDO room){
		return roomDao.update(room);
	}
	
	@Override
	public int remove(Long id){
		return roomDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return roomDao.batchRemove(ids);
	}

	@Override
	public int checkCode(RoomDO room) {
		return roomDao.checkCode(room);
	}

	@Override
	public int batchInsert(List<RoomDO> roomDOList) {
		return roomDao.batchInsert(roomDOList);
	}

	@Override
	public int synchronization(String orderId) {
		return roomDao.synchronization(orderId);
	}

	@Override
	public void duplicate(String orderId) {
		roomDao.duplicate(orderId);
	}

	@Override
	public void removeCopy(String orderId) {
		roomDao.removeCopy(orderId);
	}

	@Override
	public List<DictDO> listByType(Map<String, Object> map) {
		return roomDao.listByType(map);
	}

	@Override
	public List<RoomDO> listNotHas(Query query) {
		return roomDao.listNotHas(query);
	}

	@Override
	public int countListNotHas(Query query) {
		return roomDao.countListNotHas(query);
	}

	@Override
	public void updateArea(String orderId) {
		roomDao.updateArea(orderId);
	}

	@Override
	public List<RoomContractDO> getLoukuan(Long deptId) {
		return roomDao.getLoukuan(deptId);
	}

	@Override
	public void updateRoom(Long roomId) {
		roomDao.updateRoom(roomId);
	}

	@Override
	public String getSum(Map<String, Object> params) {
		return roomDao.getSum(params);
	}


}
