package com.bootdo.business.service;

import com.bootdo.business.domain.PayListDO;

import java.util.List;
import java.util.Map;

/**
 * 商铺合同缴费列表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-23 22:07:12
 */
public interface PayListService {

	PayListDO get(Long id);

	PayListDO getForChechang(Long id);

	List<PayListDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(PayListDO payList);

	int saveForChechang(PayListDO payList);

	int update(PayListDO payList);

	int updateForChechang(PayListDO payList);

	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
