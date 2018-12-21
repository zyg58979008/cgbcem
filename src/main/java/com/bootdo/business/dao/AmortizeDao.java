package com.bootdo.business.dao;

import com.bootdo.business.domain.AmortizeDO;

import java.util.List;
import java.util.Map;

import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.report.domain.ReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-25 09:02:54
 */
@Mapper
public interface AmortizeDao {

	AmortizeDO get(Long id);

	List<AmortizeDO> getForPay(AmortizeDO amortizeDO);

	List<AmortizeDO> list(Map<String, Object> map);
	
	List<AmortizeDO> getForShoujiaolv(AmortizeDO amortizeDO);

	AmortizeDO getForShoujiaolvTotal(AmortizeDO amortizeDO);

	List<AmortizeDO> getByCode(String contractCode);

	int count(Map<String, Object> map);
	
	int save(AmortizeDO amortize);
	
	int update(AmortizeDO amortize);

	int updateForAmortize(AmortizeDO amortize);

	int updateForPay(AmortizeDO amortize);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<AmortizeDO> shopAmortizeList(@Param("reportDOs") List<ReportDO> reportDOs,@Param("reportDOsin") ReportDO reportDOsin);

	AmortizeDO getShopAmortizeReport(@Param("reportDOs") List<ReportDO> reportDOs,@Param("reportDOsin") ReportDO reportDOsin);

    List<LeasebackDO> leaseList(@Param("reportDOs") List<ReportDO> reportDOs,@Param("reportDOsin") ReportDO reportDOsin);
}
