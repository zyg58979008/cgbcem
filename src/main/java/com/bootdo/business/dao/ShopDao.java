package com.bootdo.business.dao;


import com.bootdo.business.domain.ShopDO;
import com.bootdo.realty.domain.RoomDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
@Mapper
public interface ShopDao {

	ShopDO get(Long id);

	ShopDO getCode(String code);
	
	List<ShopDO> list(Map<String, Object> map);

	ShopDO getArea(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(ShopDO room);
	
	int update(ShopDO room);
	
	int remove(Long id);

	int batchRemove(@Param("ids") Long[] ids);

    int checkCode(ShopDO room);

	int batchInsert(@Param("shopDOList") List<ShopDO> shopDOList);

    int synchronization(@Param("orderId") String orderId);

    void duplicate(@Param("orderId") String orderId);

	int checkShopContract(ShopDO roomDO);
}
