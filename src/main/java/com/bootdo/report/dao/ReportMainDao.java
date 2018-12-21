package com.bootdo.report.dao;


import com.bootdo.realty.domain.RoomDO;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.WuyefeiDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
@Mapper
public interface ReportMainDao {

	WuyefeiDO getSum(WuyefeiDO w);

	List<WuyefeiDO> getWuyefei(WuyefeiDO w);

	List<WuyefeiDO> getQunuanfei(WuyefeiDO w);

	QunuanfeiDO getQunuanfeiSum(WuyefeiDO w);

    List<AmortizeReportDO> getWuyefeiAmortize(@Param("reportDOs") List<ReportDO> reportDOs, @Param("reportDOsin") ReportDO reportDOsin);
}
