package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 活动管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-09 15:52:38
 */
public class ActivityDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long buildingId;
	private String state;
private String contractCode;
	private Long id;
	//商铺编码
	private String shopCode;
	//签约人
	private String contractor;
	//合同实测面积
	private Double contractTrueArea;
	//合同计租面积（含20%公摊）
	private Double contractRentArea;
	//计租面积
	private Double rentArea;
	//品牌
	private String brand;
	//商铺经营类别
	private String shopType;
	//活动名称
	private String activityName;
	//活动费
	private Double activityMoney;
	//DM单
	private Double dmDan;
	//邀请卡款
	private Double cardMoney;
	//活动开始日期
	private Date startDate;
	//活动结束日期
	private Date endDate;
	//活动开始日期
	private String startDateString;
	//活动结束日期
	private String endDateString;
	//批量ID
	private String orderId;
	private Double unreceived;
	private Double activityMoneyReceived;
	private Double dmDanReceived;
	private Double cardMoneyReceived;
	private Double activityMoneyUnreceived;
	private Double dmDanUnreceived;
	private Double cardMoneyUnreceived;
	private Double activityMoneyPrice;
	private Double dmDanPrice;
	private Double cardMoneyPrice;

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Double getActivityMoneyPrice() {
		return activityMoneyPrice;
	}

	public void setActivityMoneyPrice(Double activityMoneyPrice) {
		this.activityMoneyPrice = activityMoneyPrice;
	}

	public Double getDmDanPrice() {
		return dmDanPrice;
	}

	public void setDmDanPrice(Double dmDanPrice) {
		this.dmDanPrice = dmDanPrice;
	}

	public Double getCardMoneyPrice() {
		return cardMoneyPrice;
	}

	public void setCardMoneyPrice(Double cardMoneyPrice) {
		this.cardMoneyPrice = cardMoneyPrice;
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
	 * 设置：签约人
	 */
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	/**
	 * 获取：签约人
	 */
	public String getContractor() {
		return contractor;
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
	 * 设置：品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：品牌
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：商铺经营类别
	 */
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	/**
	 * 获取：商铺经营类别
	 */
	public String getShopType() {
		return shopType;
	}
	/**
	 * 设置：活动名称
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * 获取：活动名称
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * 设置：活动费
	 */
	public void setActivityMoney(Double activityMoney) {
		this.activityMoney = activityMoney;
	}
	/**
	 * 获取：活动费
	 */
	public Double getActivityMoney() {
		return activityMoney;
	}
	/**
	 * 设置：DM单
	 */
	public void setDmDan(Double dmDan) {
		this.dmDan = dmDan;
	}
	/**
	 * 获取：DM单
	 */
	public Double getDmDan() {
		return dmDan;
	}
	/**
	 * 设置：邀请卡款
	 */
	public void setCardMoney(Double cardMoney) {
		this.cardMoney = cardMoney;
	}
	/**
	 * 获取：邀请卡款
	 */
	public Double getCardMoney() {
		return cardMoney;
	}
	/**
	 * 设置：活动开始日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：活动开始日期
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：活动结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：活动结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getUnreceived() {
		return unreceived;
	}

	public void setUnreceived(Double unreceived) {
		this.unreceived = unreceived;
	}

	public Double getActivityMoneyReceived() {
		return activityMoneyReceived;
	}

	public void setActivityMoneyReceived(Double activityMoneyReceived) {
		this.activityMoneyReceived = activityMoneyReceived;
	}

	public Double getDmDanReceived() {
		return dmDanReceived;
	}

	public void setDmDanReceived(Double dmDanReceived) {
		this.dmDanReceived = dmDanReceived;
	}

	public Double getCardMoneyReceived() {
		return cardMoneyReceived;
	}

	public void setCardMoneyReceived(Double cardMoneyReceived) {
		this.cardMoneyReceived = cardMoneyReceived;
	}

	public Double getActivityMoneyUnreceived() {
		return activityMoneyUnreceived;
	}

	public void setActivityMoneyUnreceived(Double activityMoneyUnreceived) {
		this.activityMoneyUnreceived = activityMoneyUnreceived;
	}

	public Double getDmDanUnreceived() {
		return dmDanUnreceived;
	}

	public void setDmDanUnreceived(Double dmDanUnreceived) {
		this.dmDanUnreceived = dmDanUnreceived;
	}

	public Double getCardMoneyUnreceived() {
		return cardMoneyUnreceived;
	}

	public void setCardMoneyUnreceived(Double cardMoneyUnreceived) {
		this.cardMoneyUnreceived = cardMoneyUnreceived;
	}
}
