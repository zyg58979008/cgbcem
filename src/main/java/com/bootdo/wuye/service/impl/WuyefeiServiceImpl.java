package com.bootdo.wuye.service.impl;

import com.bootdo.realty.domain.RoomDO;
import com.bootdo.wuye.dao.WuyefeiDao;
import com.bootdo.wuye.service.WuyefeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WuyefeiServiceImpl implements WuyefeiService {
	@Autowired
	private WuyefeiDao wuyefeiDao;


	@Override
	public void update(RoomDO r) {
		wuyefeiDao.update(r);
	}
}
