package com.bootdo.wuye.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-08 08:29:07
 */
public class WuyefeiDetailDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//状态
	private String state;
	//开始年度
	private String startYear;
	//结束年度
	private String endYear;
	//
	private Date startDate;
	//
	private Date endDate;
	//未付
	private Double unpay;
	//已付
	private Double payed;
	//
	private Long buildingId;

	private Double buildingArea;

	private String buildingName;

	private String name;
	//
	private String roomCode;
	//
	private String roomType;
	//单元
	private Integer unit;
	//楼层
	private Integer floor;
	//
	private Long roomId;
	//应付
	private Double yingpay;
	//应付
	private Double wuyefei;
	//头表ID
	private Long wuyefeiId;
	//折扣
	private Double zhekou;
	//
	private String idCard;
	//折扣
	private Double months;

	private String type;

	private boolean isChanged;
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：开始年度
	 */
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	/**
	 * 获取：开始年度
	 */
	public String getStartYear() {
		return startYear;
	}
	/**
	 * 设置：结束年度
	 */
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	/**
	 * 获取：结束年度
	 */
	public String getEndYear() {
		return endYear;
	}
	/**
	 * 设置：
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：未付
	 */
	public void setUnpay(Double unpay) {
		this.unpay = unpay;
	}
	/**
	 * 获取：未付
	 */
	public Double getUnpay() {
		return unpay;
	}
	/**
	 * 设置：已付
	 */
	public void setPayed(Double payed) {
		this.payed = payed;
	}
	/**
	 * 获取：已付
	 */
	public Double getPayed() {
		return payed;
	}
	/**
	 * 设置：
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * 获取：
	 */
	public Long getBuildingId() {
		return buildingId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	/**
	 * 设置：应付
	 */
	public void setYingpay(Double yingpay) {
		this.yingpay = yingpay;
	}
	/**
	 * 获取：应付
	 */
	public Double getYingpay() {
		return yingpay;
	}
	/**
	 * 设置：头表ID
	 */
	public void setWuyefeiId(Long wuyefeiId) {
		this.wuyefeiId = wuyefeiId;
	}
	/**
	 * 获取：头表ID
	 */
	public Long getWuyefeiId() {
		return wuyefeiId;
	}
	/**
	 * 设置：折扣
	 */
	public void setZhekou(Double zhekou) {
		this.zhekou = zhekou;
	}
	/**
	 * 获取：折扣
	 */
	public Double getZhekou() {
		return zhekou;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}

	public Double getWuyefei() {
		return wuyefei;
	}

	public void setWuyefei(Double wuyefei) {
		this.wuyefei = wuyefei;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Double getMonths() {
		return months;
	}

	public void setMonths(Double months) {
		this.months = months;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean changed) {
		isChanged = changed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
