package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 商铺合同缴费列表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-09 00:14:17
 */
public class ActivityPayListDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double activityMoneyPrice;
	private Double dmDanPrice;
	private Double cardMoneyPrice;
	private Long id;
	//
	private String deptName;
	//活动名称
	private String activityName;
	//状态(0正常，1冲销)
	private String state;
	//楼宇id
	private Long buildingId;
	//楼宇名称
	private String buildingName;
	//商铺id
	private Long shopId;
	//商铺编码
	private String shopCode;
	//
	private Integer floor;
	//
	private String typeName;
	//交来类型
	private String sType;
	//
	private String sTypeName;
	//单据编号
	private String code;
	//
	private Double price;
	//
	private String payType;
	//收款人
	private String receiptBy;
	//付款人
	private String payBy;
	//品牌收款人
	private String brandReceiptBy;
	//银行卡号
	private String cardNo;
	//
	private String bank;
	//冲销ID
	private String chongxiaoCode;
	//
	private Long area;
	//业务ID
	private Long yewuId;
	//
	private String brand;
	//打印次数
	private Integer print;
	//姓名
	private String name;
	//日期
	private String date;
	//缴费日期
	private Date payDate;
	//唯一ID
	private String oid;
	//
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getsTypeName() {
		return sTypeName;
	}

	public void setsTypeName(String sTypeName) {
		this.sTypeName = sTypeName;
	}

	/**
	 * 设置：
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：
	 */
	public String getDeptName() {
		return deptName;
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
	 * 设置：状态(0正常，1冲销)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态(0正常，1冲销)
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：楼宇id
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * 获取：楼宇id
	 */
	public Long getBuildingId() {
		return buildingId;
	}
	/**
	 * 设置：楼宇名称
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	/**
	 * 获取：楼宇名称
	 */
	public String getBuildingName() {
		return buildingName;
	}
	/**
	 * 设置：商铺id
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：商铺id
	 */
	public Long getShopId() {
		return shopId;
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
	 * 设置：
	 */
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	/**
	 * 获取：
	 */
	public Integer getFloor() {
		return floor;
	}
	/**
	 * 设置：
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取：
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * 设置：交来类型
	 */
	public void setSType(String sType) {
		this.sType = sType;
	}
	/**
	 * 获取：交来类型
	 */
	public String getSType() {
		return sType;
	}
	/**
	 * 设置：
	 */
	public void setSTypeName(String sTypeName) {
		this.sTypeName = sTypeName;
	}
	/**
	 * 获取：
	 */
	public String getSTypeName() {
		return sTypeName;
	}
	/**
	 * 设置：单据编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：单据编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：收款人
	 */
	public void setReceiptBy(String receiptBy) {
		this.receiptBy = receiptBy;
	}
	/**
	 * 获取：收款人
	 */
	public String getReceiptBy() {
		return receiptBy;
	}
	/**
	 * 设置：付款人
	 */
	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}
	/**
	 * 获取：付款人
	 */
	public String getPayBy() {
		return payBy;
	}
	/**
	 * 设置：品牌收款人
	 */
	public void setBrandReceiptBy(String brandReceiptBy) {
		this.brandReceiptBy = brandReceiptBy;
	}
	/**
	 * 获取：品牌收款人
	 */
	public String getBrandReceiptBy() {
		return brandReceiptBy;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * 设置：
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * 获取：
	 */
	public String getBank() {
		return bank;
	}
	/**
	 * 设置：冲销ID
	 */
	public void setChongxiaoCode(String chongxiaoCode) {
		this.chongxiaoCode = chongxiaoCode;
	}
	/**
	 * 获取：冲销ID
	 */
	public String getChongxiaoCode() {
		return chongxiaoCode;
	}
	/**
	 * 设置：
	 */
	public void setArea(Long area) {
		this.area = area;
	}
	/**
	 * 获取：
	 */
	public Long getArea() {
		return area;
	}
	/**
	 * 设置：业务ID
	 */
	public void setYewuId(Long yewuId) {
		this.yewuId = yewuId;
	}
	/**
	 * 获取：业务ID
	 */
	public Long getYewuId() {
		return yewuId;
	}
	/**
	 * 设置：
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：打印次数
	 */
	public void setPrint(Integer print) {
		this.print = print;
	}
	/**
	 * 获取：打印次数
	 */
	public Integer getPrint() {
		return print;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：日期
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取：日期
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 设置：缴费日期
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * 获取：缴费日期
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * 设置：唯一ID
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * 获取：唯一ID
	 */
	public String getOid() {
		return oid;
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
}
