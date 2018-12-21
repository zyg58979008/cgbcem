package com.bootdo.realty.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomContractLog;
import com.bootdo.report.domain.Payment;
import com.bootdo.report.domain.RoomContractMoneyDO;
import com.bootdo.wuye.domain.WuyefeiDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 09:05:58
 */
@Mapper
public interface RoomContractDao {

	RoomContractDO get(Long id);
	
	List<RoomContractDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoomContractDO contract);
	
	int update(RoomContractDO contract);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchInsert(@Param("roomContractDOList") List<RoomContractDO> roomContractDOList);

    void saveLog(RoomContractLog roomContractLog);

    List<RoomContractLog> log(Map<String, Object> map);

	int countLog(Query query);

	List<String> getIds(@Param("deptId") Long deptId);

    List<RoomContractDO> listPay(Query query);

	int countPay(Query query);

    String getId(@Param("s")String s,@Param("deptId") Long deptId);

	List<String> getRoom(Long deptId);

	int savePay(PayListDO payListDO);

	void savePayLog(RoomContractLog roomContractLog);

    List<PayListDO> pay(Map<String, Object> map);

    int countPayList(Query query);

	PayListDO getPay(Long id);

	void updatePay(PayListDO payListDO);

    int checkRoom(@Param("ids") Long[] ids);

    RoomContractDO getByRoomId(@Param("id") Long id);

    void updateRoomSellType(@Param("orderId") String orderId);

    List<Payment> paymentList(Map<String, Object> params);

    List<RoomContractMoneyDO> contractList(Map<String, Object> params);

    WuyefeiDO getPaySum(Map<String, Object> params);
}
