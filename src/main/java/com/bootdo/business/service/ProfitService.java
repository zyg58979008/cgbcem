package com.bootdo.business.service;


import com.bootdo.business.domain.ProfitDO;
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
public interface ProfitService {
	
	ProfitDO get(Long id);

	List<ProfitDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProfitDO room);
	
	int update(ProfitDO room);
	
	int remove(ProfitDO room);

    int checkHas(Map<String, Object> map);

	void removeMoney(Long id);

	void update7(Long id);

    void callLeaseback(Long id, Long deptId);
}
