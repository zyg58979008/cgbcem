package com.bootdo.common.controller;

import com.bootdo.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}
	public Long getDeptId() {
		return getUser().getDeptId();
	}
	public String getUsername() {
		return getUser().getUsername();
	}
}