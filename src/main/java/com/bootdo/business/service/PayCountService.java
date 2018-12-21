package com.bootdo.business.service;

import com.bootdo.business.domain.PayCountDO;
import com.bootdo.business.domain.PayListDO;
import com.bootdo.report.domain.ReportDO;

import java.util.List;
import java.util.Map;

/**
 * 商铺合同缴费管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-19 13:50:07
 */
public interface PayCountService {
	
	PayCountDO get(Long id);

	PayCountDO getByCo(Long id);
	
	List<PayCountDO> list(Map<String, Object> map);

	List<PayCountDO> shopContractList(List<ReportDO> reportDOs, ReportDO reportDOsin);

	PayCountDO getShopContractReport(List<ReportDO> reportDOs,ReportDO reportDOsin);

	List<PayCountDO> shopContractTypeList(List<ReportDO> reportDOs, ReportDO reportDOsin);

	List<PayCountDO> getNumForContractEnd(List<ReportDO> reportDOs, ReportDO reportDOsin);

	List<PayCountDO> getNumForAmortizeMonth(List<ReportDO> reportDOs, ReportDO reportDOsin);

	PayCountDO getShopContractTypeReport(List<ReportDO> reportDOs,ReportDO reportDOsin);

	List<PayCountDO> getCode(String contractCode);


	int count(Map<String, Object> map);
	
	int save(PayCountDO payCount);
	
	int update(PayCountDO payCount);

	int getUnpayedCount(PayCountDO payCount);

	int updatePrice(PayCountDO payCount);

	int updateFourOther(PayCountDO payCount);

	void updatePriceChechang(PayCountDO payCount);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchInsert(List<PayCountDO> payCountDOList);

    void savePayFinal();

	void deleteCount();

	void updateAfterChechang(PayCountDO payCountDO);

	void updateNowChechangTotal(PayCountDO payCountDO);

	PayCountDO getBeforeChechangTotal(PayCountDO payCountDO);

	PayCountDO getNowChechangTotal(PayCountDO payCountDO);
}
