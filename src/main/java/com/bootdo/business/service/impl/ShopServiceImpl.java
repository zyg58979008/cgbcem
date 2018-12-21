package com.bootdo.business.service.impl;

import com.bootdo.business.dao.ShopDao;
import com.bootdo.business.domain.ShopDO;
import com.bootdo.business.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public ShopDO get(Long id){
		return shopDao.get(id);
	}

	@Override
	public ShopDO getCode(String code){
		return shopDao.getCode(code);
	}

	@Override
	public List<ShopDO> list(Map<String, Object> map){
		return shopDao.list(map);
	}

	@Override
	public ShopDO getArea(Map<String, Object> map){
		return shopDao.getArea(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return shopDao.count(map);
	}
	
	@Override
	public int save(ShopDO room){
		room.preInsert();
		return shopDao.save(room);
	}
	
	@Override
	public int update(ShopDO room){
		return shopDao.update(room);
	}
	
	@Override
	public int remove(Long id){
		return shopDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return shopDao.batchRemove(ids);
	}

	@Override
	public int checkCode(ShopDO room) {
		return shopDao.checkCode(room);
	}

	@Override
	public int batchInsert(List<ShopDO> roomDOList) {
		return shopDao.batchInsert(roomDOList);
	}

	@Override
	public int synchronization(String orderId) {
		return shopDao.synchronization(orderId);
	}

	@Override
	public void duplicate(String orderId) {
		shopDao.duplicate(orderId);
	}

	@Override
	public int checkShopContract(ShopDO roomDO) {
		return shopDao.checkShopContract(roomDO);
	}

}
