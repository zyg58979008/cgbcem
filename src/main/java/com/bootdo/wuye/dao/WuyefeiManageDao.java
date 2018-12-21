package com.bootdo.wuye.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.wuye.domain.ExportDO;
import com.bootdo.wuye.domain.WuyeAmortize;
import com.bootdo.wuye.domain.WuyefeiDO;
import com.bootdo.wuye.domain.WuyefeiDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-08 08:29:06
 */
@Mapper
public interface WuyefeiManageDao {

	WuyefeiDO get(Long id);
	
	List<WuyefeiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WuyefeiDO wuyefei);
	
	int update(WuyefeiDO wuyefei);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    void batchInsert(WuyefeiDO wuyefeiDO);

	int checkRoomNum(WuyefeiDO wuyefeiDO);

	int batchUpdate(WuyefeiDO wuyefei);

    int checkWuyefei(Long id);

    List<WuyefeiDetailDO> detailList(Query query);

    WuyefeiDetailDO getDetail(Long id);

	void removeDetail(Long id);

    int updateDetail(WuyefeiDetailDO wuyefeiDetailDO);

    int countDetail(Query query);

    String getId(String s);

	int savePay(PayListDO payListDO);

	void updateCount(Long wuyefeiId);

    void insertDetail(WuyefeiDO wuyefei);

    List<PayListDO> pay(Query query);

	int countPayList(Query query);

    PayListDO getPay(Long id);

	int updatePay(PayListDO payListDO);

    void updateMoney(Long id);

    List<RoomDO> getWuyefeiRoom(WuyefeiDO wuyefei);

    void batchAmortizes(@Param("wuyeAmortizes") List<WuyeAmortize> wuyeAmortizes);

    int checkHasWuyefei(WuyefeiDO wuyefei);

    List<WuyeAmortize> getWuyeAmortize(WuyefeiDetailDO wuyefeiId);

    void updateAmortize(WuyeAmortize w);

    void removeAmortize(WuyeAmortize wuyeAmortize);

    List<AmortizeReportDO> wuyeAmortizeList(@Param("reportDOs") List<ReportDO> reportDOs,@Param("reportDOsin") ReportDO reportDOsin);

    AmortizeReportDO getWuyeAmortizeReport(Map<String, Object> params);

    void batchInsertLastYear(WuyefeiDO wuyefei);

    List<RoomDO> getWuyefeiRoomHas(WuyefeiDO wuyefei);

    void removeDetailById(@Param("id") Long id);

    void batchInsertByRoom(WuyefeiDO wuyefei);

    void batchInsertLastYearByRoom(WuyefeiDO wuyefei);

    List<RoomDO> getWuyefeiRoomHasByRoom(WuyefeiDO wuyefei);

    List<RoomDO> getWuyefeiRoomByRoom(WuyefeiDO wuyefei);

    WuyefeiDO getSum(WuyefeiDO wuyefeiDO);

    List<String> getWuyefeiDetail(String id);

    List<WuyeAmortize> getWuyeAmortizeByCode(WuyefeiDetailDO wuyefeiDetailDO);

    void updateDetailPay(WuyefeiDetailDO wuyefeiDetailDO);

    WuyefeiDetailDO getDetailByRoom(WuyefeiDetailDO wuyefeiDetailDO);

    WuyefeiDO getPaySum(Map<String, Object> params);

    ExportDO getExport(Long deptId);

    void removeExport(Long deptId);

    void saveExport(ExportDO exportDO);
}
