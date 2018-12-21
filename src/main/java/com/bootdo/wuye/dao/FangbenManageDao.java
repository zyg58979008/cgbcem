package com.bootdo.wuye.dao;


import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.wuye.domain.FangbenDO;
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
public interface FangbenManageDao {

	FangbenDO get(Long id);

	List<FangbenDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(FangbenDO room);

	int update(FangbenDO room);

	int remove(Long id);

	int batchRemove(@Param("ids") Long[] ids);

    void removeNotPay(FangbenDO fangbenDO);

    List<FangbenDO> listPay(Query query);

	int countPay(Query query);

    List<PayListDO> pay(Query query);

	int countPayList(Query query);
}
