package com.bootdo.business.service.impl;

import com.bootdo.business.domain.PayListDO;
import com.bootdo.report.domain.ReportDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.business.dao.PayCountDao;
import com.bootdo.business.domain.PayCountDO;
import com.bootdo.business.service.PayCountService;



@Service
public class PayCountServiceImpl implements PayCountService {
	@Autowired
	private PayCountDao payCountDao;
	
	@Override
	public PayCountDO get(Long id){
		return payCountDao.get(id);
	}

	@Override
	public PayCountDO getByCo(Long id){
		return payCountDao.getByCo(id);
	}

	@Override
	public List<PayCountDO> list(Map<String, Object> map){
		return payCountDao.list(map);
	}

	@Override
	public List<PayCountDO> getCode(String contractCode){
		return payCountDao.getCode(contractCode);
	}

	@Override
	public int count(Map<String, Object> map){
		return payCountDao.count(map);
	}
	
	@Override
	public int save(PayCountDO payCount){
		return payCountDao.save(payCount);
	}
	
	@Override
	public int update(PayCountDO payCount){
		return payCountDao.update(payCount);
	}

	@Override
	public int getUnpayedCount(PayCountDO payCount){
		return payCountDao.getUnpayedCount(payCount);
	}

	@Override
	public int updatePrice(PayCountDO payCount){
		return payCountDao.updatePrice(payCount);
	}
	@Override
	public int updateFourOther(PayCountDO payCount){
		return payCountDao.updateFourOther(payCount);
	}

	@Override
	public void updatePriceChechang(PayCountDO payCount){
			payCountDao.updatePriceChechang(payCount);
	}

	@Override
	public int remove(Long id){
		return payCountDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return payCountDao.batchRemove(ids);
	}
	@Override
	public int batchInsert(List<PayCountDO> payCountDOList) {
		return payCountDao.batchInsert(payCountDOList);
	}

	@Override
	public void savePayFinal(){
		payCountDao.savePayFinal();
	}

	@Override
	public void deleteCount(){
		payCountDao.deleteCount();
	}

	@Override
	public void updateAfterChechang(PayCountDO payCountDO){
		payCountDao.updateAfterChechang(payCountDO);
	}

	@Override
	public void updateNowChechangTotal(PayCountDO payCountDO){
		payCountDao.updateNowChechangTotal(payCountDO);
	}

	@Override
	public PayCountDO getBeforeChechangTotal(PayCountDO payCountDO){
		return payCountDao.getBeforeChechangTotal(payCountDO);
	}

	@Override
	public PayCountDO getNowChechangTotal(PayCountDO payCountDO){
		return payCountDao.getNowChechangTotal(payCountDO);
	}

	@Override
	public List<PayCountDO> shopContractList(List<ReportDO>reportDOs, ReportDO reportDOsin) {
		return payCountDao.shopContractList(reportDOs,reportDOsin);
	}

	@Override
	public PayCountDO getShopContractReport(List<ReportDO>reportDOs,ReportDO reportDOsin) {
		return payCountDao.getShopContractReport(reportDOs,reportDOsin);
	}

	@Override
	public List<PayCountDO> shopContractTypeList(List<ReportDO>reportDOs, ReportDO reportDOsin) {
		return payCountDao.shopContractTypeList(reportDOs,reportDOsin);
	}

	@Override
	public List<PayCountDO> getNumForContractEnd(List<ReportDO>reportDOs, ReportDO reportDOsin) {
		return payCountDao.getNumForContractEnd(reportDOs,reportDOsin);
	}

	@Override
	public List<PayCountDO> getNumForAmortizeMonth(List<ReportDO>reportDOs, ReportDO reportDOsin) {
		return payCountDao.getNumForAmortizeMonth(reportDOs,reportDOsin);
	}

	@Override
	public PayCountDO getShopContractTypeReport(List<ReportDO>reportDOs,ReportDO reportDOsin) {
		return payCountDao.getShopContractTypeReport(reportDOs,reportDOsin);
	}
}
