package com.bootdo.realty.service.impl;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.dao.RoomContractDao;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomContractLog;
import com.bootdo.realty.service.RoomContractService;
import com.bootdo.report.domain.Payment;
import com.bootdo.report.domain.RoomContractMoneyDO;
import com.bootdo.wuye.domain.WuyefeiDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class RoomContractServiceImpl implements RoomContractService {
	@Autowired
	private RoomContractDao contractDao;
	
	@Override
	public RoomContractDO get(Long id){
		return contractDao.get(id);
	}
	
	@Override
	public List<RoomContractDO> list(Map<String, Object> map){
		return contractDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contractDao.count(map);
	}
	
	@Override
	public int save(RoomContractDO contract){
		return contractDao.save(contract);
	}
	
	@Override
	public int update(RoomContractDO contract){
		return contractDao.update(contract);
	}
	
	@Override
	public int remove(Long id){
		return contractDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return contractDao.batchRemove(ids);
	}

	@Override
	public int batchInsert(List<RoomContractDO> roomContractDOList) {
		return contractDao.batchInsert(roomContractDOList);
	}

	@Override
	public void saveLog(RoomContractLog roomContractLog) {
		contractDao.saveLog(roomContractLog);
	}

	@Override
	public List<RoomContractLog> log(Map<String, Object> params) {
		return contractDao.log(params);
	}

	@Override
	public int countLog(Query query) {
		return contractDao.countLog(query);
	}

	@Override
	public List<String> getIds(Long deptId) {
		return contractDao.getIds(deptId);
	}

	@Override
	public List<RoomContractDO> listPay(Query query) {
		return contractDao.listPay(query);
	}

	@Override
	public int countPay(Query query) {
		return contractDao.countPay(query);
	}

	@Override
	public String getId(String s, Long deptId) {
		return contractDao.getId(s,deptId);
	}

	@Override
	public List<String> getRoom(Long deptId) {
		return contractDao.getRoom(deptId);
	}

	@Override
	public int savePay(PayListDO payListDO) {
		return contractDao.savePay(payListDO);
	}

	@Override
	public void savePayLog(RoomContractLog roomContractLog) {
		contractDao.savePayLog(roomContractLog);
	}

	@Override
	public List<PayListDO> pay(Map<String, Object> params) {
		return contractDao.pay(params);
	}

	@Override
	public int countPayList(Query query) {
		return contractDao.countPayList(query);
	}

	@Override
	public PayListDO getPay(Long id) {
		return contractDao.getPay(id);
	}

	@Override
	public void updatePay(PayListDO payListDO) {
		contractDao.updatePay(payListDO);
	}

	@Override
	public int checkRoom(Long[] ids) {
		return contractDao.checkRoom(ids);
	}

	@Override
	public RoomContractDO getByRoomId(Long id) {
		return contractDao.getByRoomId(id);
	}

	@Override
	public void updateRoomSellType(String orderId) {
		contractDao.updateRoomSellType(orderId);
	}

	@Override
	public List<Payment> paymentList(Map<String, Object> params) {
		return contractDao.paymentList(params);
	}

	@Override
	public List<RoomContractMoneyDO> contractList(Map<String, Object> params) {
		return contractDao.contractList(params);
	}

	@Override
	public WuyefeiDO getPaySum(Map<String, Object> params) {
		return contractDao.getPaySum(params);
	}

}
