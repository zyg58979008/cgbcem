package com.bootdo.business.dao;

import com.bootdo.business.domain.*;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.Query;
import com.bootdo.report.domain.ReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商铺合同管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-06 20:31:35
 */
@Mapper
public interface ContractDao {

	int countPayList(Query query);

	int getCountForSaledShop(String shopCode);

	int countPayListForChechang(Query query);

	ContractDO get(Long id);

	List<String> getShop(Long deptId);

	List<String> getCodes(Long deptId);

	List<ContractDO> reportlist(Long deptId);

	List<PayListDO> pay(Map<String, Object> map);

	List<PayListDO> payForChechang(Map<String, Object> map);

	void savePayLog(ContractLog contractLog);

	ContractDO getByCode(String code);

	void updateTanxiaoForChechang(ContractDO contractDO);

	void updateTanxiaoForChechangD(ContractDO contractDO);
	void saveContractBf(String oid);
	void savePayCountBf(String oid);
	void saveAmortizeBf(String oid);

	void deletePayCountBf(String oid);
	void deleteContractBf(String oid);
	void deleteAmortizeBf(String oid);
	void deletePayListChechang(String oid);
	void revertContract(String oid);
	void revertPayCount(String oid);
	void revertAmortize(String oid);
	void deleteAmortizeForRevert(ContractDO contractDO);

	void updateTanxiaoCurrentMonth(AmortizeDO amortizeDO);

	void updateTanxiaoOperationMonth(AmortizeDO amortizeDO);

	void insertTanxiao(AmortizeDO amortizeDO);

	ContractDO getByOid(String oid);

	AmortizeDO getTanxiaoFenleiPrice(AmortizeDO amortizeDO);

	List<ContractDO> list(Map<String, Object> map);

	List<ContractDO> taizhangList(Map<String, Object> map);

	List<ContractDO> listForReport(Map<String, Object> map);

	ContractDO totalForReport(Map<String, Object> map);

	List<ContractDO> cheChangList(Map<String, Object> map);

	int count(Map<String, Object> map);

	int chechangCount(Map<String, Object> map);

	List<ContractDO> listDetail(Map<String, Object> map);

	int countDetail(Map<String, Object> map);
	
	int save(ContractDO contract);

    int updateOld(String contractCode);

	int update(ContractDO contract);

	int updateChechangContract(ContractDO contract);

	int updateLog(ContractLog contractLog);

	int updateToContract(ToContractDO toContractDO);

	int insertAddToCon(ToContractDO toContractDO);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int batchInsert(@Param("shopContractDOList") List<ContractDO> shopContractDOList);

	void updateToContractState();

	int batchInsertTanxiao(@Param("tanxiaoDOList") List<AmortizeDO> tanxiaoDOList);

	void batchInsertToShop(@Param("toContractDOList") List<ToContractDO> toContractDOList);

	void saveLog(ContractLog contractLog);

	String[]  getDoubleByCo ();

	ContractDO getDate(@Param("buildingId") Long buildingId,@Param("shopCode") String shopCode);

	List<ContractLog> log(Map<String, Object> map);

	int countLog(Query query);

	String getId(@Param("s")String s,@Param("deptId") Long deptId);

	List<ReportDO>getNumForType(Long deptId);

	List<ReportDO>getNumForAreaA(Long deptId);

	List<ReportDO>getNumForAreaB(Long deptId);

	List<ReportDO>getNumForShoujiaolv(Long deptId);

	List<DictDO>shopShouType();
}
