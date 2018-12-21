package com.bootdo.realty.service;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomContractLog;
import com.bootdo.report.domain.Payment;
import com.bootdo.report.domain.RoomContractMoneyDO;
import com.bootdo.wuye.domain.WuyefeiDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 09:05:58
 */
public interface RoomContractService {
	
	RoomContractDO get(Long id);
	
	List<RoomContractDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomContractDO contract);
	
	int update(RoomContractDO contract);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchInsert(List<RoomContractDO> roomContractDOList);

    void saveLog(RoomContractLog roomContractLog);

    List<RoomContractLog> log(Map<String, Object> query);

	int countLog(Query query);

	List<String> getIds(Long deptId);

    List<RoomContractDO> listPay(Query query);

	int countPay(Query query);

    String getId(String s, Long deptId);

    List<String> getRoom(Long deptId);

	int savePay(PayListDO payListDO);

	void savePayLog(RoomContractLog roomContractLog);

    List<PayListDO> pay(Map<String, Object> params);

    int countPayList(Query query);

	PayListDO getPay(Long id);

	void updatePay(PayListDO payListDO);

    int checkRoom(Long[] ids);

    RoomContractDO getByRoomId(Long id);

    void updateRoomSellType(String orderId);

    List<Payment> paymentList(Map<String, Object> params);

    List<RoomContractMoneyDO> contractList(Map<String, Object> params);

    WuyefeiDO getPaySum(Map<String, Object> params);
}
