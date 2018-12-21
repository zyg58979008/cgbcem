package com.bootdo.report.domain;

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
public class RoomContractMoneyDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
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
	//转让类型或未售
	private String sellType;
	//转让类型或未售
	private String sellTypeName;
	//单价
	private Double price;
	//总价
	private Double totalPrice;
	//总价
	private Double yishou;
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

	private String date1;
	private String date2;
	private String date3;
	private String date4;
	private String date5;
	private String date6;
	private String date7;
	private String date8;
	private String date9;
	private String date10;
	private String date11;
	private String date12;
	private String date13;
	private String date14;
	private String date15;
	private String date16;
	private String date17;
	private String date18;
	private String date19;
	private String date20;
	private String date21;
	private String date22;
	private String date23;
	private String date24;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public Double getFirstPay() {
		return firstPay;
	}

	public void setFirstPay(Double firstPay) {
		this.firstPay = firstPay;
	}

	public Double getFirstReceive() {
		return firstReceive;
	}

	public void setFirstReceive(Double firstReceive) {
		this.firstReceive = firstReceive;
	}

	public Double getFirstOwn() {
		return firstOwn;
	}

	public void setFirstOwn(Double firstOwn) {
		this.firstOwn = firstOwn;
	}

	public Double getLoan() {
		return loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}

	public String getSellBy() {
		return sellBy;
	}

	public void setSellBy(String sellBy) {
		this.sellBy = sellBy;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getBalanceArea() {
		return balanceArea;
	}

	public void setBalanceArea(Double balanceArea) {
		this.balanceArea = balanceArea;
	}

	public Double getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Double balancePrice) {
		this.balancePrice = balancePrice;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTotalPriceNoTax() {
		return totalPriceNoTax;
	}

	public void setTotalPriceNoTax(Double totalPriceNoTax) {
		this.totalPriceNoTax = totalPriceNoTax;
	}

	public String getTaxMethod() {
		return taxMethod;
	}

	public void setTaxMethod(String taxMethod) {
		this.taxMethod = taxMethod;
	}

	public String getTaxMethodName() {
		return taxMethodName;
	}

	public void setTaxMethodName(String taxMethodName) {
		this.taxMethodName = taxMethodName;
	}

	public Double getTaxed() {
		return taxed;
	}

	public void setTaxed(Double taxed) {
		this.taxed = taxed;
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Date getJiaofangDate() {
		return jiaofangDate;
	}

	public void setJiaofangDate(Date jiaofangDate) {
		this.jiaofangDate = jiaofangDate;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getDate3() {
		return date3;
	}

	public void setDate3(String date3) {
		this.date3 = date3;
	}

	public String getDate4() {
		return date4;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	public String getDate5() {
		return date5;
	}

	public void setDate5(String date5) {
		this.date5 = date5;
	}

	public String getDate6() {
		return date6;
	}

	public void setDate6(String date6) {
		this.date6 = date6;
	}

	public String getDate7() {
		return date7;
	}

	public void setDate7(String date7) {
		this.date7 = date7;
	}

	public String getDate8() {
		return date8;
	}

	public void setDate8(String date8) {
		this.date8 = date8;
	}

	public String getDate9() {
		return date9;
	}

	public void setDate9(String date9) {
		this.date9 = date9;
	}

	public String getDate10() {
		return date10;
	}

	public void setDate10(String date10) {
		this.date10 = date10;
	}

	public String getDate11() {
		return date11;
	}

	public void setDate11(String date11) {
		this.date11 = date11;
	}

	public String getDate12() {
		return date12;
	}

	public void setDate12(String date12) {
		this.date12 = date12;
	}

	public String getDate13() {
		return date13;
	}

	public void setDate13(String date13) {
		this.date13 = date13;
	}

	public String getDate14() {
		return date14;
	}

	public void setDate14(String date14) {
		this.date14 = date14;
	}

	public String getDate15() {
		return date15;
	}

	public void setDate15(String date15) {
		this.date15 = date15;
	}

	public String getDate16() {
		return date16;
	}

	public void setDate16(String date16) {
		this.date16 = date16;
	}

	public String getDate17() {
		return date17;
	}

	public void setDate17(String date17) {
		this.date17 = date17;
	}

	public String getDate18() {
		return date18;
	}

	public void setDate18(String date18) {
		this.date18 = date18;
	}

	public String getDate19() {
		return date19;
	}

	public void setDate19(String date19) {
		this.date19 = date19;
	}

	public String getDate20() {
		return date20;
	}

	public void setDate20(String date20) {
		this.date20 = date20;
	}

	public String getDate21() {
		return date21;
	}

	public void setDate21(String date21) {
		this.date21 = date21;
	}

	public String getDate22() {
		return date22;
	}

	public void setDate22(String date22) {
		this.date22 = date22;
	}

	public String getDate23() {
		return date23;
	}

	public void setDate23(String date23) {
		this.date23 = date23;
	}

	public String getDate24() {
		return date24;
	}

	public void setDate24(String date24) {
		this.date24 = date24;
	}
}
