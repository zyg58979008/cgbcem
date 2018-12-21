package com.bootdo.wuye.domain;

import com.bootdo.base.domain.OwnerDO;
import com.bootdo.common.domain.BaseDO;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-16 19:30:23
 */
public class FangbenDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long buildingId;
	//
	private String buildingName;
	//
	private Long roomId;
	//未收维修基金
	private Double weixiuUnpay;
	//已收维修基金
	private Double weixiuPayer;
	//应收维修基金
	private Double weixiuYing;
	//未收交易费
	private Double jiaoyiUnpay;
	//已收交易费
	private Double jiaoyiPayer;
	//应收交易费
	private Double jiaoyiYing;
	//未收房产证
	private Double fangbenUnpay;
	//已收房产证
	private Double fangbenPayer;
	//应收房产证
	private Double fangbenYing;
	//未收契税
	private Double qishuiUnpay;
	//已收契税
	private Double qishuiPayer;
	//应收契税
	private Double qishuiYing;
	//未收代办费
	private Double daibanUnpay;
	//已收代办费
	private Double daibanPayer;
	//应收代办费
	private Double daibanYing;

	private String name;

	private String idCard;

	private String phone;

	private Double totalPrice;

	private Double yishou;

	private Double weishou;

	private String code;

	private int unit;

	private int floor;

	private Double buildingArea;

	private String payTypeName;

	private String payType;

	private String roomTypeName;

	private String roomType;

	private Double price;

	private Double qkjiaoyi;

	private Double qkFangben;

	private Double qkQishui;

	private Double qkDaiban;

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
	 * 设置：未收维修基金
	 */
	public void setWeixiuUnpay(Double weixiuUnpay) {
		this.weixiuUnpay = weixiuUnpay;
	}
	/**
	 * 获取：未收维修基金
	 */
	public Double getWeixiuUnpay() {
		return weixiuUnpay;
	}
	/**
	 * 设置：已收维修基金
	 */
	public void setWeixiuPayer(Double weixiuPayer) {
		this.weixiuPayer = weixiuPayer;
	}
	/**
	 * 获取：已收维修基金
	 */
	public Double getWeixiuPayer() {
		return weixiuPayer;
	}
	/**
	 * 设置：应收维修基金
	 */
	public void setWeixiuYing(Double weixiuYing) {
		this.weixiuYing = weixiuYing;
	}
	/**
	 * 获取：应收维修基金
	 */
	public Double getWeixiuYing() {
		return weixiuYing;
	}
	/**
	 * 设置：未收交易费
	 */
	public void setJiaoyiUnpay(Double jiaoyiUnpay) {
		this.jiaoyiUnpay = jiaoyiUnpay;
	}
	/**
	 * 获取：未收交易费
	 */
	public Double getJiaoyiUnpay() {
		return jiaoyiUnpay;
	}
	/**
	 * 设置：已收交易费
	 */
	public void setJiaoyiPayer(Double jiaoyiPayer) {
		this.jiaoyiPayer = jiaoyiPayer;
	}
	/**
	 * 获取：已收交易费
	 */
	public Double getJiaoyiPayer() {
		return jiaoyiPayer;
	}
	/**
	 * 设置：应收交易费
	 */
	public void setJiaoyiYing(Double jiaoyiYing) {
		this.jiaoyiYing = jiaoyiYing;
	}
	/**
	 * 获取：应收交易费
	 */
	public Double getJiaoyiYing() {
		return jiaoyiYing;
	}
	/**
	 * 设置：未收房产证
	 */
	public void setFangbenUnpay(Double fangbenUnpay) {
		this.fangbenUnpay = fangbenUnpay;
	}
	/**
	 * 获取：未收房产证
	 */
	public Double getFangbenUnpay() {
		return fangbenUnpay;
	}
	/**
	 * 设置：已收房产证
	 */
	public void setFangbenPayer(Double fangbenPayer) {
		this.fangbenPayer = fangbenPayer;
	}
	/**
	 * 获取：已收房产证
	 */
	public Double getFangbenPayer() {
		return fangbenPayer;
	}
	/**
	 * 设置：应收房产证
	 */
	public void setFangbenYing(Double fangbenYing) {
		this.fangbenYing = fangbenYing;
	}
	/**
	 * 获取：应收房产证
	 */
	public Double getFangbenYing() {
		return fangbenYing;
	}
	/**
	 * 设置：未收契税
	 */
	public void setQishuiUnpay(Double qishuiUnpay) {
		this.qishuiUnpay = qishuiUnpay;
	}
	/**
	 * 获取：未收契税
	 */
	public Double getQishuiUnpay() {
		return qishuiUnpay;
	}
	/**
	 * 设置：已收契税
	 */
	public void setQishuiPayer(Double qishuiPayer) {
		this.qishuiPayer = qishuiPayer;
	}
	/**
	 * 获取：已收契税
	 */
	public Double getQishuiPayer() {
		return qishuiPayer;
	}
	/**
	 * 设置：应收契税
	 */
	public void setQishuiYing(Double qishuiYing) {
		this.qishuiYing = qishuiYing;
	}
	/**
	 * 获取：应收契税
	 */
	public Double getQishuiYing() {
		return qishuiYing;
	}
	/**
	 * 设置：未收代办费
	 */
	public void setDaibanUnpay(Double daibanUnpay) {
		this.daibanUnpay = daibanUnpay;
	}
	/**
	 * 获取：未收代办费
	 */
	public Double getDaibanUnpay() {
		return daibanUnpay;
	}
	/**
	 * 设置：已收代办费
	 */
	public void setDaibanPayer(Double daibanPayer) {
		this.daibanPayer = daibanPayer;
	}
	/**
	 * 获取：已收代办费
	 */
	public Double getDaibanPayer() {
		return daibanPayer;
	}
	/**
	 * 设置：应收代办费
	 */
	public void setDaibanYing(Double daibanYing) {
		this.daibanYing = daibanYing;
	}
	/**
	 * 获取：应收代办费
	 */
	public Double getDaibanYing() {
		return daibanYing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getYishou() {
		return yishou;
	}

	public void setYishou(Double yishou) {
		this.yishou = yishou;
	}

	public Double getWeishou() {
		return weishou;
	}

	public void setWeishou(Double weishou) {
		this.weishou = weishou;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Double getQkjiaoyi() {
		return qkjiaoyi;
	}

	public void setQkjiaoyi(Double qkjiaoyi) {
		this.qkjiaoyi = qkjiaoyi;
	}

	public Double getQkFangben() {
		return qkFangben;
	}

	public void setQkFangben(Double qkFangben) {
		this.qkFangben = qkFangben;
	}

	public Double getQkQishui() {
		return qkQishui;
	}

	public void setQkQishui(Double qkQishui) {
		this.qkQishui = qkQishui;
	}

	public Double getQkDaiban() {
		return qkDaiban;
	}

	public void setQkDaiban(Double qkDaiban) {
		this.qkDaiban = qkDaiban;
	}
}
