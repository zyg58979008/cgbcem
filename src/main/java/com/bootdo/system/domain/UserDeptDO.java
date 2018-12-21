package com.bootdo.system.domain;

public class UserDeptDO {
	private Long id;
	private Long  userId;
	private Long deptId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
