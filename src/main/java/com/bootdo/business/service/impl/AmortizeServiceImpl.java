package com.bootdo.business.service.impl;

import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.report.domain.ReportDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.business.dao.AmortizeDao;
import com.bootdo.business.domain.AmortizeDO;
import com.bootdo.business.service.AmortizeService;



@Service
public class AmortizeServiceImpl implements AmortizeService {
	@Autowired
	private AmortizeDao amortizeDao;
	
	@Override
	public AmortizeDO get(Long id){
		return amortizeDao.get(id);
	}
	@Override
	public List<AmortizeDO> getForPay(AmortizeDO amortizeDO){
		return amortizeDao.getForPay(amortizeDO);
	}

	@Override
	public List<AmortizeDO> list(Map<String, Object> map){
		return amortizeDao.list(map);
	}

	@Override
	public List<AmortizeDO> getForShoujiaolv(AmortizeDO amortizeDO){
		return amortizeDao.getForShoujiaolv(amortizeDO);
	}

	@Override
	public AmortizeDO getForShoujiaolvTotal(AmortizeDO amortizeDO){
		return amortizeDao.getForShoujiaolvTotal(amortizeDO);
	}

	@Override
	public List<AmortizeDO> getByCode(String contractCode){
		return amortizeDao.getByCode(contractCode);
	}

	@Override
	public int count(Map<String, Object> map){
		return amortizeDao.count(map);
	}
	
	@Override
	public int save(AmortizeDO amortize){
		return amortizeDao.save(amortize);
	}
	
	@Override
	public int update(AmortizeDO amortize){
		return amortizeDao.update(amortize);
	}

	@Override
	public int updateForAmortize(AmortizeDO amortize){
		return amortizeDao.updateForAmortize(amortize);
	}

	@Override
	public int updateForPay(AmortizeDO amortize){
		return amortizeDao.updateForPay(amortize);
	}

	@Override
	public int remove(Long id){
		return amortizeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return amortizeDao.batchRemove(ids);
	}

	@Override
	public List<AmortizeDO> shopAmortizeList(List<ReportDO>reportDOs,ReportDO reportDOsin) {
		return amortizeDao.shopAmortizeList(reportDOs,reportDOsin);
	}

	@Override
	public AmortizeDO getShopAmortizeReport(List<ReportDO>reportDOs,ReportDO reportDOsin) {
		return amortizeDao.getShopAmortizeReport(reportDOs,reportDOsin);
	}

	@Override
	public List<LeasebackDO> leaseList(List<ReportDO> reportDOs, ReportDO reportDOsin) {
		return amortizeDao.leaseList(reportDOs,reportDOsin);
	}

}
