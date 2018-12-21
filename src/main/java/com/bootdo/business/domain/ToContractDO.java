package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-30 20:55:11
 */
public class ToContractDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String state;
	//
	private Long id;
	//楼宇ID
	private Long buildingId;
	//商铺编码
	private String shopCode;
	//合同编号
	private String contractCode;
	//楼层
	private Integer floor;
	//用途
	private String roomType;
	//项目ID
	private Long deptId;
	//合同实测面积
	private Double contractTrueArea;
	//合同计租面积（含20%公摊）
	private Double contractRentArea;
	private Long shopId;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	//计租面积
	private Double rentArea;
	//是否删除  -1：已删除  0：正常
	private String delFlag;
	//
	private String orderId;
	//唯一ID
	private String oid;
	//所续签的合同唯一ID
	public String getOidOld() {
		return oidOld;
	}

	public void setOidOld(String oidOld) {
		this.oidOld = oidOld;
	}

	//唯一ID
	private String oidOld;


	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * 设置：楼宇ID
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * 获取：楼宇ID
	 */
	public Long getBuildingId() {
		return buildingId;
	}
	/**
	 * 设置：商铺编码
	 */
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	/**
	 * 获取：商铺编码
	 */
	public String getShopCode() {
		return shopCode;
	}
	/**
	 * 设置：合同编号
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * 获取：合同编号
	 */
	public String getContractCode() {
		return contractCode;
	}
	/**
	 * 设置：楼层
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getFloor() {
		return floor;
	}
	/**
	 * 设置：用途
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	/**
	 * 获取：用途
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * 设置：项目ID
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：项目ID
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：合同实测面积
	 */
	public void setContractTrueArea(Double contractTrueArea) {
		this.contractTrueArea = contractTrueArea;
	}
	/**
	 * 获取：合同实测面积
	 */
	public Double getContractTrueArea() {
		return contractTrueArea;
	}
	/**
	 * 设置：合同计租面积（含20%公摊）
	 */
	public void setContractRentArea(Double contractRentArea) {
		this.contractRentArea = contractRentArea;
	}
	/**
	 * 获取：合同计租面积（含20%公摊）
	 */
	public Double getContractRentArea() {
		return contractRentArea;
	}
	/**
	 * 设置：计租面积
	 */
	public void setRentArea(Double rentArea) {
		this.rentArea = rentArea;
	}
	/**
	 * 获取：计租面积
	 */
	public Double getRentArea() {
		return rentArea;
	}
	/**
	 * 设置：是否删除  -1：已删除  0：正常
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  0：正常
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：
	 */
	public String getOrderId() {
		return orderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
