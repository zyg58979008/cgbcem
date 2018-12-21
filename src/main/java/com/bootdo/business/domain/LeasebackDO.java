package com.bootdo.business.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-28 20:27:52
 */
public class LeasebackDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Date normalStartDate;
	//
	private Date normalEndDate;
	//
	private Date startDate;
	//
	private Date endDate;

	private Date month;
	//天数
	private Integer day;
	//金额
	private Double ying;
	//金额
	private Double unpay;
	//金额
	private Double payed;
	//金额
	private Double price;
	//
	private String orderId;
	//
	private Long roomId;
	//
	private Long buildingId;
	//
	private Long deptId;
	//
	private String roomCode;
	private String buildingName;
	//0正常，1按7%返
	private String type;

	private int dayFour;

	private int dayFive;
	private int unit;
	private int floor;

	private String year;

	private String status;

	private Long profitId;


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
	 * 设置：天数
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * 获取：天数
	 */
	public Integer getDay() {
		return day;
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
	/**
	 * 设置：
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
	 * 获取：
	 */
	public Long getRoomId() {
		return roomId;
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
	/**
	 * 设置：
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：
	 */
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	/**
	 * 获取：
	 */
	public String getRoomCode() {
		return roomCode;
	}
	/**
	 * 设置：0正常，1按7%返
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：0正常，1按7%返
	 */
	public String getType() {
		return type;
	}

	public int getDayFour() {
		return dayFour;
	}

	public void setDayFour(int dayFour) {
		this.dayFour = dayFour;
	}

	public int getDayFive() {
		return dayFive;
	}

	public void setDayFive(int dayFive) {
		this.dayFive = dayFive;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getYing() {
		return ying;
	}

	public void setYing(Double ying) {
		this.ying = ying;
	}

	public Double getUnpay() {
		return unpay;
	}

	public void setUnpay(Double unpay) {
		this.unpay = unpay;
	}

	public Double getPayed() {
		return payed;
	}

	public void setPayed(Double payed) {
		this.payed = payed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getNormalStartDate() {
		return normalStartDate;
	}

	public void setNormalStartDate(Date normalStartDate) {
		this.normalStartDate = normalStartDate;
	}

	public Date getNormalEndDate() {
		return normalEndDate;
	}

	public void setNormalEndDate(Date normalEndDate) {
		this.normalEndDate = normalEndDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getProfitId() {
		return profitId;
	}

	public void setProfitId(Long profitId) {
		this.profitId = profitId;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
}
