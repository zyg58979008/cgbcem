package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 巡检区域管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:07:35
 */
public class AreaDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long areaId;
	//上级区域ID，一级区域为0
	private Long parentId;
	//部门名称
	private String name;
	//排序
	private Integer orderNum;
	//是否删除  -1：已删除  0：正常
	private Integer delFlag;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
