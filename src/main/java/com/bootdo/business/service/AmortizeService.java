package com.bootdo.business.service;

import com.bootdo.business.domain.AmortizeDO;
import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.report.domain.ReportDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-25 09:02:54
 */
public interface AmortizeService {
	
	AmortizeDO get(Long id);
	
	List<AmortizeDO> list(Map<String, Object> map);

	List<AmortizeDO> getForShoujiaolv(AmortizeDO amortizeDO);

	AmortizeDO getForShoujiaolvTotal(AmortizeDO amortizeDO);

	List<AmortizeDO> getByCode(String contractCode);

	int count(Map<String, Object> map);
	
	int save(AmortizeDO amortize);
	
	int update(AmortizeDO amortize);

	int updateForAmortize(AmortizeDO amortize);

	int updateForPay(AmortizeDO amortize);

	List<AmortizeDO> getForPay(AmortizeDO amortize);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<AmortizeDO> shopAmortizeList(List<ReportDO> reportDOs,ReportDO reportDOsin);

	AmortizeDO getShopAmortizeReport(List<ReportDO> reportDOs,ReportDO reportDOsin);


    List<LeasebackDO> leaseList(List<ReportDO> reportDOs, ReportDO reportDOsin);
}
