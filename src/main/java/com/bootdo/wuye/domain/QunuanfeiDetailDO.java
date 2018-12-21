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
public class QunuanfeiDetailDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//状态
	private String state;
	//状态
	private String type;
	//开始年度
	private String startYear;
	//结束年度
	private String endYear;
	//
	private Date startDate;
	//
	private Date endDate;
	//
	private Long buildingId;

	private Double buildingArea;

	private String buildingName;
	//
	private String roomType;
	//
	private String roomTypeName;

	private String roomCode;
	//单元
	private Integer unit;
	//楼层
	private Integer floor;
	//
	private Long roomId;
	//头表ID
	private Long qunuanfeiId;
	//未付取暖费
	private Double qunuanUnpay;
	//已付取暖费
	private Double qunuanPayed;
	//应付取暖费
	private Double qunuanYing;
	//未付停暖费
	private Double tingnuanUnpay;
	//已付停暖费
	private Double tingnuanPay;
	//应付停暖费
	private Double tingnuanYing;
	//层高
	private Double height;
	//系数
	private Double ratio;
	//单价
	private Double price;
	//系数乘以单价
	private Double p;
	//
	private String idCard;
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

	public Long getQunuanfeiId() {
		return qunuanfeiId;
	}

	public void setQunuanfeiId(Long qunuanfeiId) {
		this.qunuanfeiId = qunuanfeiId;
	}

	public Double getQunuanUnpay() {
		return qunuanUnpay;
	}

	public void setQunuanUnpay(Double qunuanUnpay) {
		this.qunuanUnpay = qunuanUnpay;
	}

	public Double getQunuanPayed() {
		return qunuanPayed;
	}

	public void setQunuanPayed(Double qunuanPayed) {
		this.qunuanPayed = qunuanPayed;
	}

	public Double getQunuanYing() {
		return qunuanYing;
	}

	public void setQunuanYing(Double qunuanYing) {
		this.qunuanYing = qunuanYing;
	}

	public Double getTingnuanUnpay() {
		return tingnuanUnpay;
	}

	public void setTingnuanUnpay(Double tingnuanUnpay) {
		this.tingnuanUnpay = tingnuanUnpay;
	}

	public Double getTingnuanPay() {
		return tingnuanPay;
	}

	public void setTingnuanPay(Double tingnuanPay) {
		this.tingnuanPay = tingnuanPay;
	}

	public Double getTingnuanYing() {
		return tingnuanYing;
	}

	public void setTingnuanYing(Double tingnuanYing) {
		this.tingnuanYing = tingnuanYing;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getP() {
		return p;
	}

	public void setP(Double p) {
		this.p = p;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
}
