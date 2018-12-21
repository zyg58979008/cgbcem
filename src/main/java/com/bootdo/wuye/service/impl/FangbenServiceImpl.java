package com.bootdo.wuye.service.impl;

import com.bootdo.realty.domain.RoomDO;
import com.bootdo.wuye.dao.FangbenDao;
import com.bootdo.wuye.dao.WuyefeiDao;
import com.bootdo.wuye.service.FangbenService;
import com.bootdo.wuye.service.WuyefeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FangbenServiceImpl implements FangbenService {
	@Autowired
	private FangbenDao fangbenDao;


	@Override
	public void update(RoomDO r) {
		fangbenDao.update(r);
	}
}
