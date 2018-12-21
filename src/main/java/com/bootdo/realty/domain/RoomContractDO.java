package com.bootdo.realty.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 09:05:58
 */
public class RoomContractDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	//楼宇ID
	private Long buildingId;
	//楼宇ID
	private String buildingName;
	//楼宇ID
	private String parentBuildingName;
	//房屋ID
	private Long roomId;
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
	//转让类型或未售
	private String sellType;
	//转让类型或未售
	private String sellTypeName;
	//单价
	private Double price;
	//总价
	private Double totalPrice;
	//总价
	private Double totalPrice1;
	//总价
	private Double yishou;
	//总价
	private Double yishou1;
	//总价
	private Double weishou;

	//付款方式（0全款，1贷款）
	private String payType;
	//付款方式（0全款，1贷款）
	private String payTypeName;
	//首付款
	private Double firstPay;
	//已收首付款
	private Double firstReceive;
	//欠(首付）款
	private Double firstOwn;
	//贷款金额
	private Double loan;
	//销售顾问
	private String sellBy;
	//销售日期
	private Date sellDate;
	//备注
	private String remark;
	//合同编号
	private String code;
	//退补面积
	private Double balanceArea;
	//退补金额
	private Double balancePrice;
	//退补金额
	private Double balancePayed;
	//退补金额
	private Double balanceUnpay;
	//税款
	private Double tax;
	//房屋结算价款(不含税)
	private Double totalPriceNoTax;
	//计税方法
	private String taxMethod;
	//计税方法
	private String taxMethodName;
	//已预缴营业税
	private Double taxed;
	//购房者姓名
	private String name;
	//购房者身份证号
	private String idCard;
	//购房者电话号
	private String phone;
	//开户行
	private String bank;
	//银行卡号
	private String cardNo;
	//状态
	private String state;
	//状态
	private String bumen;
	//状态
	private String renyuan;
	//状态
	private String leixing;
	//状态
	private String guishu;
	//状态
	private String beizhu;
	//批量ID
	private String orderId;

	private Date jiaofangDate;

	private Double depositYing;

	private Double depositPayed;

	private Double renchouYing;

	private Double renchouPayed;

	private Double zhekou;

	private String isTotal;

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
	 * 设置：单元数
	 */
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	/**
	 * 获取：单元数
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
	 * 设置：房屋类型
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	/**
	 * 获取：房屋类型
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
	/**
	 * 设置：转让类型或未售
	 */
	public void setSellType(String sellType) {
		this.sellType = sellType;
	}
	/**
	 * 获取：转让类型或未售
	 */
	public String getSellType() {
		return sellType;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 设置：总价
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取：总价
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置：付款方式（0全款，1贷款）
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：付款方式（0全款，1贷款）
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：首付款
	 */
	public void setFirstPay(Double firstPay) {
		this.firstPay = firstPay;
	}
	/**
	 * 获取：首付款
	 */
	public Double getFirstPay() {
		return firstPay;
	}
	/**
	 * 设置：已收首付款
	 */
	public void setFirstReceive(Double firstReceive) {
		this.firstReceive = firstReceive;
	}
	/**
	 * 获取：已收首付款
	 */
	public Double getFirstReceive() {
		return firstReceive;
	}
	/**
	 * 设置：欠(首付）款
	 */
	public void setFirstOwn(Double firstOwn) {
		this.firstOwn = firstOwn;
	}
	/**
	 * 获取：欠(首付）款
	 */
	public Double getFirstOwn() {
		return firstOwn;
	}
	/**
	 * 设置：贷款金额
	 */
	public void setLoan(Double loan) {
		this.loan = loan;
	}
	/**
	 * 获取：贷款金额
	 */
	public Double getLoan() {
		return loan;
	}
	/**
	 * 设置：销售顾问
	 */
	public void setSellBy(String sellBy) {
		this.sellBy = sellBy;
	}
	/**
	 * 获取：销售顾问
	 */
	public String getSellBy() {
		return sellBy;
	}
	/**
	 * 设置：销售日期
	 */
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	/**
	 * 获取：销售日期
	 */
	public Date getSellDate() {
		return sellDate;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：合同编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：合同编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：退补面积
	 */
	public void setBalanceArea(Double balanceArea) {
		this.balanceArea = balanceArea;
	}
	/**
	 * 获取：退补面积
	 */
	public Double getBalanceArea() {
		return balanceArea;
	}
	/**
	 * 设置：退补金额
	 */
	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}
	/**
	 * 获取：退补金额
	 */
	public Double getBalancePrice() {
		return balancePrice;
	}
	/**
	 * 设置：税款
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}
	/**
	 * 获取：税款
	 */
	public Double getTax() {
		return tax;
	}
	/**
	 * 设置：房屋结算价款(不含税)
	 */
	public void setTotalPriceNoTax(Double totalPriceNoTax) {
		this.totalPriceNoTax = totalPriceNoTax;
	}
	/**
	 * 获取：房屋结算价款(不含税)
	 */
	public Double getTotalPriceNoTax() {
		return totalPriceNoTax;
	}
	/**
	 * 设置：计税方法
	 */
	public void setTaxMethod(String taxMethod) {
		this.taxMethod = taxMethod;
	}
	/**
	 * 获取：计税方法
	 */
	public String getTaxMethod() {
		return taxMethod;
	}
	/**
	 * 设置：已预缴营业税
	 */
	public void setTaxed(Double taxed) {
		this.taxed = taxed;
	}
	/**
	 * 获取：已预缴营业税
	 */
	public Double getTaxed() {
		return taxed;
	}
	/**
	 * 设置：购房者姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：购房者姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：购房者身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：购房者身份证号
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置：购房者电话号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：购房者电话号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：开户行
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * 获取：开户行
	 */
	public String getBank() {
		return bank;
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

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getSellTypeName() {
		return sellTypeName;
	}

	public void setSellTypeName(String sellTypeName) {
		this.sellTypeName = sellTypeName;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getTaxMethodName() {
		return taxMethodName;
	}

	public void setTaxMethodName(String taxMethodName) {
		this.taxMethodName = taxMethodName;
	}

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}

	public String getRenyuan() {
		return renyuan;
	}

	public void setRenyuan(String renyuan) {
		this.renyuan = renyuan;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getGuishu() {
		return guishu;
	}

	public void setGuishu(String guishu) {
		this.guishu = guishu;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public Date getJiaofangDate() {
		return jiaofangDate;
	}

	public void setJiaofangDate(Date jiaofangDate) {
		this.jiaofangDate = jiaofangDate;
	}

	public Double getDepositYing() {
		return depositYing;
	}

	public void setDepositYing(Double depositYing) {
		this.depositYing = depositYing;
	}

	public Double getDepositPayed() {
		return depositPayed;
	}

	public void setDepositPayed(Double depositPayed) {
		this.depositPayed = depositPayed;
	}

	public Double getRenchouYing() {
		return renchouYing;
	}

	public void setRenchouYing(Double renchouYing) {
		this.renchouYing = renchouYing;
	}

	public Double getRenchouPayed() {
		return renchouPayed;
	}

	public void setRenchouPayed(Double renchouPayed) {
		this.renchouPayed = renchouPayed;
	}

	public String getIsTotal() {
		return isTotal;
	}

	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}

	public Double getBalancePayed() {
		return balancePayed;
	}

	public void setBalancePayed(Double balancePayed) {
		this.balancePayed = balancePayed;
	}

	public Double getBalanceUnpay() {
		return balanceUnpay;
	}

	public void setBalanceUnpay(Double balanceUnpay) {
		this.balanceUnpay = balanceUnpay;
	}

	public Double getTotalPrice1() {
		return totalPrice1;
	}

	public void setTotalPrice1(Double totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}

	public Double getYishou1() {
		return yishou1;
	}

	public void setYishou1(Double yishou1) {
		this.yishou1 = yishou1;
	}

	public Double getZhekou() {
		return zhekou;
	}

	public void setZhekou(Double zhekou) {
		this.zhekou = zhekou;
	}

	public String getParentBuildingName() {
		return parentBuildingName;
	}

	public void setParentBuildingName(String parentBuildingName) {
		this.parentBuildingName = parentBuildingName;
	}
}
