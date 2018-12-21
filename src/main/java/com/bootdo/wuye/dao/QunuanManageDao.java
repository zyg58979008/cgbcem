package com.bootdo.wuye.dao;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.QunuanfeiDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-08 08:29:06
 */
@Mapper
public interface QunuanManageDao {

	QunuanfeiDO get(Long id);
	
	List<QunuanfeiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QunuanfeiDO qunuanfeiDO);
	
	int update(QunuanfeiDO qunuanfeiDO);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchInsert(QunuanfeiDO qunuanfeiDODO);

	int checkRoomNum(QunuanfeiDO qunuanfeiDO);

	int batchUpdate(QunuanfeiDO qunuanfeiDO);

    int checkQunuanfei(Long id);

    List<QunuanfeiDetailDO> detailList(Query query);

    QunuanfeiDetailDO getDetail(Long id);

	void removeDetail(Long id);

    int updateDetail(QunuanfeiDetailDO qunuanfeiDODetailDO);

    int countDetail(Query query);

    String getId(String s);

	int savePay(PayListDO payListDO);

	void updateCount(Long qunuanfeiDOId);

    void insertDetail(QunuanfeiDO qunuanfeiDO);

    List<PayListDO> pay(Query query);

	int countPayList(Query query);

    PayListDO getPay(Long id);

	int updatePay(PayListDO payListDO);

	void updateDetailId(@Param("id") Long id,@Param("orderId") String orderId);

	List<String> getRoomHas(@Param("id") String id);

    QunuanfeiDO getSum(QunuanfeiDO w);

    QunuanfeiDO getPaySum(Map<String, Object> params);

    QunuanfeiDetailDO getSumMoney(Map<String, Object> params);
}
