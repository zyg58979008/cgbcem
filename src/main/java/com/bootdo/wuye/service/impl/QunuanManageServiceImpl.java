package com.bootdo.wuye.service.impl;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.wuye.dao.QunuanManageDao;
import com.bootdo.wuye.dao.WuyefeiManageDao;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.QunuanfeiDetailDO;
import com.bootdo.wuye.service.QunuanManageService;
import com.bootdo.wuye.service.WuyefeiManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QunuanManageServiceImpl implements QunuanManageService {
	@Autowired
	private QunuanManageDao qunuanManageDao;

	@Override
	public QunuanfeiDO get(Long id){
		return qunuanManageDao.get(id);
	}

	@Override
	public List<QunuanfeiDO> list(Map<String, Object> map){
		return qunuanManageDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return qunuanManageDao.count(map);
	}

	@Override
	public int save(QunuanfeiDO wuyefei){
		return qunuanManageDao.save(wuyefei);
	}

	@Override
	public int update(QunuanfeiDO wuyefei){
		return qunuanManageDao.update(wuyefei);
	}

	@Override
	public int remove(Long id){
		return qunuanManageDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return qunuanManageDao.batchRemove(ids);
	}

	@Override
	public int batchInsert(QunuanfeiDO qunuanfeiDO) {
		return qunuanManageDao.batchInsert(qunuanfeiDO);
	}

	@Override
	public int checkRoomNum(QunuanfeiDO qunuanfeiDO) {
		return qunuanManageDao.checkRoomNum(qunuanfeiDO);
	}

	@Override
	public int batchUpdate(QunuanfeiDO wuyefei) {
		return qunuanManageDao.batchUpdate(wuyefei);
    }

	@Override
	public int checkQunuanfei(Long id) {
		return qunuanManageDao.checkQunuanfei(id);
	}

	@Override
	public List<QunuanfeiDetailDO> detailList(Query query) {
		return qunuanManageDao.detailList(query);
	}

	@Override
	public QunuanfeiDetailDO getDetail(Long id) {
		return qunuanManageDao.getDetail(id);
	}

	@Override
	public void removeDetail(Long id) {
		qunuanManageDao.removeDetail(id);
	}

	@Override
	public int updateDetail(QunuanfeiDetailDO qunuanfeiDetailDO) {
		return qunuanManageDao.updateDetail(qunuanfeiDetailDO);
	}

	@Override
	public int countDetail(Query query) {
		return qunuanManageDao.countDetail(query);
	}

	@Override
	public String getId(String s) {
		return qunuanManageDao.getId(s);
	}

	@Override
	public int savePay(PayListDO payListDO) {
		return qunuanManageDao.savePay(payListDO);
	}

	@Override
	public void updateCount(Long wuyefeiId) {
		qunuanManageDao.updateCount(wuyefeiId);
	}

	@Override
	public void insertDetail(QunuanfeiDO wuyefei) {
		qunuanManageDao.insertDetail(wuyefei);
	}

	@Override
	public PayListDO getPay(Long id) {
		return qunuanManageDao.getPay(id);
	}

	@Override
	public int updatePay(PayListDO payListDO) {
		return qunuanManageDao.updatePay(payListDO);
	}

	@Override
	public void updateDetailId(Long id, String orderId) {
		qunuanManageDao.updateDetailId(id,orderId);
	}

	@Override
	public List<String> getRoomHas(String id) {
		return qunuanManageDao.getRoomHas(id);
	}

	@Override
	public QunuanfeiDO getSum(QunuanfeiDO w) {
		return qunuanManageDao.getSum(w);
	}

	@Override
	public QunuanfeiDO getPaySum(Map<String, Object> params) {
		return qunuanManageDao.getPaySum(params);
	}

	@Override
	public QunuanfeiDetailDO getSumMoney(Map<String, Object> params) {
		return qunuanManageDao.getSumMoney(params);
	}


}
