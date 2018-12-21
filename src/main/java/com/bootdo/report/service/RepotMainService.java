package com.bootdo.report.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.realty.domain.BuildingDO;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.WuyefeiDO;

import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 *
 */
public interface RepotMainService {

	WuyefeiDO getSum(WuyefeiDO w);

	List<WuyefeiDO> getWuyefei(WuyefeiDO w);

    List<WuyefeiDO> getQunuanfei(WuyefeiDO w);

	QunuanfeiDO getQunuanfeiSum(WuyefeiDO w);

	List<AmortizeReportDO> getWuyefeiAmortize(List<ReportDO> reportDOs, ReportDO reportDOsin);
}
