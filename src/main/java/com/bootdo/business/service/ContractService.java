package com.bootdo.business.service;

import com.bootdo.business.domain.*;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.Query;
import com.bootdo.report.domain.ReportDO;

import java.util.List;
import java.util.Map;

/**
 * 商铺合同管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-06 20:31:35
 */
public interface ContractService {

	List<PayListDO> pay(Map<String, Object> params);

	int countPayList(Query query);

	int getCountForSaledShop(String shopCode);

	List<PayListDO> payForChechang(Map<String, Object> params);

	int countPayListForChechang(Query query);
	ContractDO get(Long id);

	void saveContractBf(String oid);
	void savePayCountBf(String oid);
	void saveAmortizeBf(String oid);
	void revertContract(String oid);
	void revertPayCount(String oid);
	void revertAmortize(String oid);
	void deleteAmortizeForRevert(ContractDO contractDO);
	void deletePayCountBf(String oid);
	void deleteContractBf(String oid);
	void deleteAmortizeBf(String oid);
	void deletePayListChechang(String oid);
	void savePayLog(ContractLog contractLog);

	ContractDO getByCode(String code);

	ContractDO getByOid(String oid);

	AmortizeDO getTanxiaoFenleiPrice(AmortizeDO amortizeDO);

	List<String> getShop(Long deptId);

	List<String> getCodes(Long deptId);

	List<ContractDO> list(Map<String, Object> map);

	List<ContractDO> taizhangList(Map<String, Object> map);

	List<ContractDO> listForReport(Map<String, Object> map);

	ContractDO totalForReport(Map<String, Object> map);

	List<ContractDO> cheChangList(Map<String, Object> map);

	List<ContractDO> listDetail(Map<String, Object> map);

	List<ContractDO> reportlist(Long deptId);

	void updateTanxiaoForChechang (ContractDO contractDO);

	void updateTanxiaoForChechangD (ContractDO contractDO);

	void updateTanxiaoCurrentMonth (AmortizeDO tanxiaoDO);

	void updateTanxiaoOperationMonth (AmortizeDO tanxiaoDO);

	void insertTanxiao (AmortizeDO tanxiaoDO);

	int count(Map<String, Object> map);

	int chechangCount(Map<String, Object> map);

	int countDetail(Map<String, Object> map);

	int save(ContractDO contract);

	int contractaddSave(ContractDO contract);

	int update(ContractDO contract);

	int updateChechangContract(ContractDO contract);

	int updateContract(ContractDO contract);

	int remove(Long id);

	int batchInsert(List<ContractDO> shopContractDOList);

	void updateToContractState();

	int batchInsertTanxiao(List<AmortizeDO> tanxiaoDOList);

	void batchInsertToShop(List<ToContractDO> toContractDOList);

	int batchRemove(Long[] ids);

	void saveLog(ContractLog contractLog);

	String[]  getDoubleByCo();

	ContractDO getDate(Long buildingId,String shopCode);

	List<ContractLog> log(Map<String, Object> query);

	int countLog(Query query);

	String getId(String s, Long deptId);

	List<ReportDO> getNumForType(Long deptId);

	List<ReportDO> getNumForAreaA(Long deptId);
	List<ReportDO> getNumForAreaB(Long deptId);

	List<ReportDO> getNumForShoujiaolv(Long deptId);

	List<DictDO> shopShouType();
}
