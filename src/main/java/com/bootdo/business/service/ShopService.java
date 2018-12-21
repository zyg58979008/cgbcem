package com.bootdo.business.service;


import com.bootdo.business.domain.ShopDO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
public interface ShopService {

	ShopDO get(Long id);

	ShopDO getCode(String code);

	List<ShopDO> list(Map<String, Object> map);

	ShopDO getArea(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ShopDO room);

	int update(ShopDO room);

	int remove(Long id);

	int batchRemove(Long[] ids);

	int checkCode(ShopDO room);

	int batchInsert(List<ShopDO> roomDOList);

	int synchronization(String orderId);

	void duplicate(String orderId);

	int checkShopContract(ShopDO roomDO);
}
