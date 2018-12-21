package com.bootdo.realty.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
public class RoomDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long contractId;
	//
	private Long[] mergeIds;
	//
	private Long[] mergedIds;
	//
	private String[] codes;
	//楼宇ID
	private Long buildingId;
	//房间编码
	private String code;
	//编码位数
	private String codeNum;
	//编码方式（0顺序,1循环）
	private String codeType;
	//单元
	private Integer unit;
	//单元
	private Integer startUnit;
	//单元
	private Integer endUnit;
	//楼层
	private Integer floor;
	//楼层
	private Integer startFloor;
	//楼层
	private Integer endFloor;
	//用途
	private String sellType;
	//用途
	private String sellTypeName;
	//用途
	private String roomType;
	//用途
	private String roomTypeName;
	//建筑面积
	private Double buildingArea;
	//使用面积
	private Double floorArea;
	//物业费
	private Double wuyefei;
	//维修基金
	private Double weixiu;
	//交易费
	private Double jiaoyifei;
	//房产证
	private Double fangchanzheng;
	//契税
	private Double qishui;
	//代办费
	private Double daibanfei;

	private List<RoomDO> roomDOList;

	private String orderId;

	private String state;

	//开始年度
	private String startYear;
	//结束年度
	private String endYear;
	//
	private Date startDate;
	//
	private Date endDate;
	//折扣
	private Double months;
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
	 * 设置：房间编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：房间编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：单元
	 */
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	/**
	 * 获取：单元
	 */
	public Integer getUnit() {
		return unit;
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
	 * 设置：建筑面积
	 */
	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}
	/**
	 * 获取：建筑面积
	 */
	public Double getBuildingArea() {
		return buildingArea;
	}
	/**
	 * 设置：使用面积
	 */
	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}
	/**
	 * 获取：使用面积
	 */
	public Double getFloorArea() {
		return floorArea;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public Integer getStartUnit() {
		return startUnit;
	}

	public void setStartUnit(Integer startUnit) {
		this.startUnit = startUnit;
	}

	public Integer getEndUnit() {
		return endUnit;
	}

	public void setEndUnit(Integer endUnit) {
		this.endUnit = endUnit;
	}

	public Integer getStartFloor() {
		return startFloor;
	}

	public void setStartFloor(Integer startFloor) {
		this.startFloor = startFloor;
	}

	public Integer getEndFloor() {
		return endFloor;
	}

	public void setEndFloor(Integer endFloor) {
		this.endFloor = endFloor;
	}

	public Long[] getMergeIds() {
		return mergeIds;
	}

	public void setMergeIds(Long[] mergeIds) {
		this.mergeIds = mergeIds;
	}

	public List<RoomDO> getRoomDOList() {
		return roomDOList;
	}

	public void setRoomDOList(List<RoomDO> roomDOList) {
		this.roomDOList = roomDOList;
	}

	public Long[] getMergedIds() {
		return mergedIds;
	}

	public void setMergedIds(Long[] mergedIds) {
		this.mergedIds = mergedIds;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Double getWuyefei() {
		return wuyefei;
	}

	public void setWuyefei(Double wuyefei) {
		this.wuyefei = wuyefei;
	}

	public Double getWeixiu() {
		return weixiu;
	}

	public void setWeixiu(Double weixiu) {
		this.weixiu = weixiu;
	}

	public Double getJiaoyifei() {
		return jiaoyifei;
	}

	public void setJiaoyifei(Double jiaoyifei) {
		this.jiaoyifei = jiaoyifei;
	}

	public Double getFangchanzheng() {
		return fangchanzheng;
	}

	public void setFangchanzheng(Double fangchanzheng) {
		this.fangchanzheng = fangchanzheng;
	}

	public Double getQishui() {
		return qishui;
	}

	public void setQishui(Double qishui) {
		this.qishui = qishui;
	}

	public Double getDaibanfei() {
		return daibanfei;
	}

	public void setDaibanfei(Double daibanfei) {
		this.daibanfei = daibanfei;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
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

	public Double getMonths() {
		return months;
	}

	public void setMonths(Double months) {
		this.months = months;
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getSellTypeName() {
		return sellTypeName;
	}

	public void setSellTypeName(String sellTypeName) {
		this.sellTypeName = sellTypeName;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
}
