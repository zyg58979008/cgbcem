package com.bootdo.business.dao;

import com.bootdo.business.domain.PayCountDO;

import java.util.List;
import java.util.Map;

import com.bootdo.report.domain.ReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商铺合同缴费管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-19 13:50:07
 */
@Mapper
public interface PayCountDao {

	PayCountDO get(Long id);

	PayCountDO getByCo(Long id);
	
	List<PayCountDO> list(Map<String, Object> map);

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

	int batchInsert(@Param("payCountDOList") List<PayCountDO> payCountDOList);

	int batchInsertFinal(@Param("payCountDOList") List<PayCountDO> payCountDOList);

	void savePayFinal();

	void deleteCount();

	void updateAfterChechang(PayCountDO payCountDO);

	void updateNowChechangTotal(PayCountDO payCountDO);

	PayCountDO getBeforeChechangTotal(PayCountDO payCountDO);

	PayCountDO getNowChechangTotal(PayCountDO payCountDO);

	List<PayCountDO> shopContractList(@Param("reportDOs") List<ReportDO> reportDOs, @Param("reportDOsin") ReportDO reportDOsin);

	PayCountDO getShopContractReport(@Param("reportDOs") List<ReportDO> reportDOs,@Param("reportDOsin") ReportDO reportDOsin);

	List<PayCountDO> shopContractTypeList(@Param("reportDOs") List<ReportDO> reportDOs, @Param("reportDOsin") ReportDO reportDOsin);

	List<PayCountDO> getNumForContractEnd(@Param("reportDOs") List<ReportDO> reportDOs, @Param("reportDOsin") ReportDO reportDOsin);

	List<PayCountDO> getNumForAmortizeMonth(@Param("reportDOs") List<ReportDO> reportDOs, @Param("reportDOsin") ReportDO reportDOsin);

	PayCountDO getShopContractTypeReport(@Param("reportDOs") List<ReportDO> reportDOs,@Param("reportDOsin") ReportDO reportDOsin);

}
