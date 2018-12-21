package com.bootdo.realty.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;


/**
 * 楼宇管理
 *
 */
public class BuildingDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	//上级ID，一级为0
	private Long parentId;
	//名称
	private String name;
	//排序
	private Integer sort;
	//单元
	private Integer unit;
	//楼宇类型
	private String roomType;


	//类型：默认0；楼宇为1.
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
}
