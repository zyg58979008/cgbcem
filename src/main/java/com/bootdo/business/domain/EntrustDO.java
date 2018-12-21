package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-27 15:53:01
 */
public class EntrustDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long buildingId;
	//房屋ID
	private Long roomId;
	//状态
	private String state;
	//开始委托年度
	private Integer entrustStartYear;
	//结束委托年度
	private Integer entrustEndYear;
	//开始委托日期
	private Date entrustStartDate;
	//结束委托日期
	private Date entrustEndDate;
	//开日返租年度
	private Integer leasebackStartYear;
	//结束返租年度
	private Integer leasebackEndYear;
	//开始返租日期
	private Date leasebackStartDate;
	//结束返租日期
	private Date leasebackEndDate;
	//批量ID
	private String orderId;
	//
	private String type;
	//楼宇ID
	private String buildingName;
	//房屋ID
	private String roomCode;
	//单元数
	private Integer unit;
	//楼层
	private Integer floor;
	//房屋类型
	private String roomType;
	//房屋类型
	private String roomTypeName;
	//建筑面积
	private Double buildingArea;
	//使用面积
	private Double floorArea;
	//单价
	private Double price;
	//总价
	private Double totalPrice;
	private String ying;
	private String pay;
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
	 * 设置：房屋ID
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
	 * 获取：房屋ID
	 */
	public Long getRoomId() {
		return roomId;
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
	 * 设置：开始委托年度
	 */
	public void setEntrustStartYear(Integer entrustStartYear) {
		this.entrustStartYear = entrustStartYear;
	}
	/**
	 * 获取：开始委托年度
	 */
	public Integer getEntrustStartYear() {
		return entrustStartYear;
	}
	/**
	 * 设置：结束委托年度
	 */
	public void setEntrustEndYear(Integer entrustEndYear) {
		this.entrustEndYear = entrustEndYear;
	}
	/**
	 * 获取：结束委托年度
	 */
	public Integer getEntrustEndYear() {
		return entrustEndYear;
	}
	/**
	 * 设置：开始委托日期
	 */
	public void setEntrustStartDate(Date entrustStartDate) {
		this.entrustStartDate = entrustStartDate;
	}
	/**
	 * 获取：开始委托日期
	 */
	public Date getEntrustStartDate() {
		return entrustStartDate;
	}
	/**
	 * 设置：结束委托日期
	 */
	public void setEntrustEndDate(Date entrustEndDate) {
		this.entrustEndDate = entrustEndDate;
	}
	/**
	 * 获取：结束委托日期
	 */
	public Date getEntrustEndDate() {
		return entrustEndDate;
	}
	/**
	 * 设置：开日返租年度
	 */
	public void setLeasebackStartYear(Integer leasebackStartYear) {
		this.leasebackStartYear = leasebackStartYear;
	}
	/**
	 * 获取：开日返租年度
	 */
	public Integer getLeasebackStartYear() {
		return leasebackStartYear;
	}
	/**
	 * 设置：结束返租年度
	 */
	public void setLeasebackEndYear(Integer leasebackEndYear) {
		this.leasebackEndYear = leasebackEndYear;
	}
	/**
	 * 获取：结束返租年度
	 */
	public Integer getLeasebackEndYear() {
		return leasebackEndYear;
	}
	/**
	 * 设置：开始返租日期
	 */
	public void setLeasebackStartDate(Date leasebackStartDate) {
		this.leasebackStartDate = leasebackStartDate;
	}
	/**
	 * 获取：开始返租日期
	 */
	public Date getLeasebackStartDate() {
		return leasebackStartDate;
	}
	/**
	 * 设置：结束返租日期
	 */
	public void setLeasebackEndDate(Date leasebackEndDate) {
		this.leasebackEndDate = leasebackEndDate;
	}
	/**
	 * 获取：结束返租日期
	 */
	public Date getLeasebackEndDate() {
		return leasebackEndDate;
	}
	/**
	 * 设置：批量ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：批量ID
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}

	public Double getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getYing() {
		return ying;
	}

	public void setYing(String ying) {
		this.ying = ying;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}
}
