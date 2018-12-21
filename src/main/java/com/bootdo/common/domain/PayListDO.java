package com.bootdo.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-11 09:31:25
 */
public class PayListDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//状态(1正常，2冲销)
	private String state;
	//姓名
	private String name;
	//业务名称
	private String title;
	//日期
	private String date;
	//楼宇id
	private Long buildingId;
	//楼宇名称
	private String buildingName;
	//房屋id
	private Long roomId;
	//房屋编码
	private String roomCode;
	//
	private Integer unit;
	//
	private Integer floor;
	//(1地产收入，2商业管理费，3商业租金，4物业不动产，5物业代办费，6取暖费，7物业费，8契税，9维修基金，10履约保证金，11质量保证金，12服务保证金)
	private String type;

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
	//
	private String brand;
	//品牌收款人
	private String brandReceiptBy;
	//银行卡号
	private String cardNo;
	//
	private String bank;
	//冲销ID
	private String chongxiaoCode;
	//冲销ID
	private String remark;
	//
	private Long area;
	//
	private Long yewuId;
	//
	private int print;

	private Date payDate;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrint() {
		return print;
	}

	public void setPrint(int print) {
		this.print = print;
	}

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
	 * 设置：状态(1正常，2冲销)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1正常，2冲销)
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
	 * 设置：房屋id
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	/**
	 * 获取：房屋id
	 */
	public Long getRoomId() {
		return roomId;
	}
	/**
	 * 设置：房屋编码
	 */
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	/**
	 * 获取：房屋编码
	 */
	public String getRoomCode() {
		return roomCode;
	}
	/**
	 * 设置：
	 */
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	/**
	 * 获取：
	 */
	public Integer getUnit() {
		return unit;
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
	 * 设置：(1地产收入，2商业管理费，3商业租金，4物业不动产，5物业代办费，6取暖费，7物业费，8契税，9维修基金，10履约保证金，11质量保证金，12服务保证金)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：(1地产收入，2商业管理费，3商业租金，4物业不动产，5物业代办费，6取暖费，7物业费，8契税，9维修基金，10履约保证金，11质量保证金，12服务保证金)
	 */
	public String getType() {
		return type;
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

	public Long getYewuId() {
		return yewuId;
	}

	public void setYewuId(Long yewuId) {
		this.yewuId = yewuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
