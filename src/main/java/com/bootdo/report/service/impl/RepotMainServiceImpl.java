package com.bootdo.report.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.realty.dao.BuildingDao;
import com.bootdo.realty.dao.RoomDao;
import com.bootdo.realty.domain.BuildingDO;
import com.bootdo.report.dao.ReportMainDao;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.report.service.RepotMainService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.WuyefeiDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RepotMainServiceImpl implements RepotMainService {
	@Autowired
	private ReportMainDao reportMainDao;
	@Override
	public WuyefeiDO getSum(WuyefeiDO w) {
		return reportMainDao.getSum(w);
	}

	@Override
	public List<WuyefeiDO> getWuyefei(WuyefeiDO w) {
		return reportMainDao.getWuyefei(w);
	}

	@Override
	public List<WuyefeiDO> getQunuanfei(WuyefeiDO w) {
		return reportMainDao.getQunuanfei(w);
	}

	@Override
	public QunuanfeiDO getQunuanfeiSum(WuyefeiDO w) {
		return reportMainDao.getQunuanfeiSum(w);
	}

	@Override
	public List<AmortizeReportDO> getWuyefeiAmortize(List<ReportDO> reportDOs, ReportDO reportDOsin) {
		return reportMainDao.getWuyefeiAmortize(reportDOs,reportDOsin);
	}
}
