package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-25 09:02:54
 */
public class AmortizeDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	//楼层 用于收缴率
	private String floors;
	//楼宇ID
	private Long buildingId;
	private Double totalZujin1;
	private Double totalWuye1;
	private Double totalGuanli1;
	private Double totalZujinUn;
	private Double totalWuyeUn;
	private Double totalGuanliUn;
	private Double totalZujin2;
	private Double totalWuye2;
	private Double totalGuanli2;

	private Double totalZujin3;
	private Double totalWuye3;
	private Double totalGuanli3;

	private Double totalZujin4;
	private Double totalWuye4;
	private Double totalGuanli4;

	private Double totalZujin5;
	private Double totalWuye5;
	private Double totalGuanli5;

	private Double totalZujin6;
	private Double totalWuye6;
	private Double totalGuanli6;

	private Double totalZujin7;
	private Double totalWuye7;
	private Double totalGuanli7;

	private Double totalZujin8;
	private Double totalWuye8;
	private Double totalGuanli8;

	private Double totalZujin9;
	private Double totalWuye9;
	private Double totalGuanli9;

	private Double totalZujin10;
	private Double totalWuye10;
	private Double totalGuanli10;

	private Double totalZujin11;
	private Double totalWuye11;
	private Double totalGuanli11;


	private Double totalZujin13;
	private Double totalWuye13;
	private Double totalGuanli13;

	private Double totalZujin12;
	private Double totalWuye12;
	private String state;

	public String getFloors() {
		return floors;
	}

	public void setFloors(String floors) {
		this.floors = floors;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getTotalZujin1() {
		return totalZujin1;
	}

	public void setTotalZujin1(Double totalZujin1) {
		this.totalZujin1 = totalZujin1;
	}

	public Double getTotalWuye1() {
		return totalWuye1;
	}

	public void setTotalWuye1(Double totalWuye1) {
		this.totalWuye1 = totalWuye1;
	}

	public Double getTotalGuanli1() {
		return totalGuanli1;
	}

	public void setTotalGuanli1(Double totalGuanli1) {
		this.totalGuanli1 = totalGuanli1;
	}

	public Double getTotalZujin2() {
		return totalZujin2;
	}

	public void setTotalZujin2(Double totalZujin2) {
		this.totalZujin2 = totalZujin2;
	}

	public Double getTotalWuye2() {
		return totalWuye2;
	}

	public void setTotalWuye2(Double totalWuye2) {
		this.totalWuye2 = totalWuye2;
	}

	public Double getTotalGuanli2() {
		return totalGuanli2;
	}

	public void setTotalGuanli2(Double totalGuanli2) {
		this.totalGuanli2 = totalGuanli2;
	}

	public Double getTotalZujin3() {
		return totalZujin3;
	}

	public void setTotalZujin3(Double totalZujin3) {
		this.totalZujin3 = totalZujin3;
	}

	public Double getTotalWuye3() {
		return totalWuye3;
	}

	public void setTotalWuye3(Double totalWuye3) {
		this.totalWuye3 = totalWuye3;
	}

	public Double getTotalGuanli3() {
		return totalGuanli3;
	}

	public void setTotalGuanli3(Double totalGuanli3) {
		this.totalGuanli3 = totalGuanli3;
	}

	public Double getTotalZujin4() {
		return totalZujin4;
	}

	public void setTotalZujin4(Double totalZujin4) {
		this.totalZujin4 = totalZujin4;
	}

	public Double getTotalWuye4() {
		return totalWuye4;
	}

	public void setTotalWuye4(Double totalWuye4) {
		this.totalWuye4 = totalWuye4;
	}

	public Double getTotalGuanli4() {
		return totalGuanli4;
	}

	public void setTotalGuanli4(Double totalGuanli4) {
		this.totalGuanli4 = totalGuanli4;
	}

	public Double getTotalZujin5() {
		return totalZujin5;
	}

	public void setTotalZujin5(Double totalZujin5) {
		this.totalZujin5 = totalZujin5;
	}

	public Double getTotalWuye5() {
		return totalWuye5;
	}

	public void setTotalWuye5(Double totalWuye5) {
		this.totalWuye5 = totalWuye5;
	}

	public Double getTotalGuanli5() {
		return totalGuanli5;
	}

	public void setTotalGuanli5(Double totalGuanli5) {
		this.totalGuanli5 = totalGuanli5;
	}

	public Double getTotalZujin6() {
		return totalZujin6;
	}

	public void setTotalZujin6(Double totalZujin6) {
		this.totalZujin6 = totalZujin6;
	}

	public Double getTotalWuye6() {
		return totalWuye6;
	}

	public void setTotalWuye6(Double totalWuye6) {
		this.totalWuye6 = totalWuye6;
	}

	public Double getTotalGuanli6() {
		return totalGuanli6;
	}

	public void setTotalGuanli6(Double totalGuanli6) {
		this.totalGuanli6 = totalGuanli6;
	}

	public Double getTotalZujin7() {
		return totalZujin7;
	}

	public void setTotalZujin7(Double totalZujin7) {
		this.totalZujin7 = totalZujin7;
	}

	public Double getTotalWuye7() {
		return totalWuye7;
	}

	public void setTotalWuye7(Double totalWuye7) {
		this.totalWuye7 = totalWuye7;
	}

	public Double getTotalGuanli7() {
		return totalGuanli7;
	}

	public void setTotalGuanli7(Double totalGuanli7) {
		this.totalGuanli7 = totalGuanli7;
	}

	public Double getTotalZujin8() {
		return totalZujin8;
	}

	public void setTotalZujin8(Double totalZujin8) {
		this.totalZujin8 = totalZujin8;
	}

	public Double getTotalWuye8() {
		return totalWuye8;
	}

	public void setTotalWuye8(Double totalWuye8) {
		this.totalWuye8 = totalWuye8;
	}

	public Double getTotalGuanli8() {
		return totalGuanli8;
	}

	public void setTotalGuanli8(Double totalGuanli8) {
		this.totalGuanli8 = totalGuanli8;
	}

	public Double getTotalZujin9() {
		return totalZujin9;
	}

	public void setTotalZujin9(Double totalZujin9) {
		this.totalZujin9 = totalZujin9;
	}

	public Double getTotalWuye9() {
		return totalWuye9;
	}

	public void setTotalWuye9(Double totalWuye9) {
		this.totalWuye9 = totalWuye9;
	}

	public Double getTotalGuanli9() {
		return totalGuanli9;
	}

	public void setTotalGuanli9(Double totalGuanli9) {
		this.totalGuanli9 = totalGuanli9;
	}

	public Double getTotalZujin10() {
		return totalZujin10;
	}

	public void setTotalZujin10(Double totalZujin10) {
		this.totalZujin10 = totalZujin10;
	}

	public Double getTotalWuye10() {
		return totalWuye10;
	}

	public void setTotalWuye10(Double totalWuye10) {
		this.totalWuye10 = totalWuye10;
	}

	public Double getTotalGuanli10() {
		return totalGuanli10;
	}

	public void setTotalGuanli10(Double totalGuanli10) {
		this.totalGuanli10 = totalGuanli10;
	}

	public Double getTotalZujin11() {
		return totalZujin11;
	}

	public void setTotalZujin11(Double totalZujin11) {
		this.totalZujin11 = totalZujin11;
	}

	public Double getTotalWuye11() {
		return totalWuye11;
	}

	public void setTotalWuye11(Double totalWuye11) {
		this.totalWuye11 = totalWuye11;
	}

	public Double getTotalGuanli11() {
		return totalGuanli11;
	}

	public void setTotalGuanli11(Double totalGuanli11) {
		this.totalGuanli11 = totalGuanli11;
	}

	public Double getTotalZujin12() {
		return totalZujin12;
	}

	public void setTotalZujin12(Double totalZujin12) {
		this.totalZujin12 = totalZujin12;
	}

	public Double getTotalWuye12() {
		return totalWuye12;
	}

	public void setTotalWuye12(Double totalWuye12) {
		this.totalWuye12 = totalWuye12;
	}

	public Double getTotalGuanli12() {
		return totalGuanli12;
	}

	public void setTotalGuanli12(Double totalGuanli12) {
		this.totalGuanli12 = totalGuanli12;
	}

	private Double totalGuanli12;

	//商户收缴率明细表日期查询条件
	private String findDate;
	//
	private Long id;
	//每天摊销钱数
	private Double amortizePrice;
	//每月租金摊销钱数
	private Double amortizePriceZujin;
	//每月物业摊销钱数
	private Double amortizePriceWuye;
	//每月管理摊销钱数
	private Double amortizePriceGuanli;

	//每月已收租金摊销钱数
	private Double amortizePriceZujinReceived;
	//每月已收物业摊销钱数
	private Double amortizePriceWuyeReceived;
	//每月已收管理摊销钱数
	private Double amortizePriceGuanliReceived;
	//计算月
	private String month;
	//商铺编号
	private String shopCode;
	//品牌
	private String brand;
	//签约人
	private String contractor;
	//计租面积
	private Double rentArea;
	//唯一ID
	private String oid;

	public String getFindDate() {
		return findDate;
	}

	public void setFindDate(String findDate) {
		this.findDate = findDate;
	}

	public Double getAmortizePriceZujinReceived() {
		return amortizePriceZujinReceived;
	}

	public void setAmortizePriceZujinReceived(Double amortizePriceZujinReceived) {
		this.amortizePriceZujinReceived = amortizePriceZujinReceived;
	}

	public Double getAmortizePriceWuyeReceived() {
		return amortizePriceWuyeReceived;
	}

	public void setAmortizePriceWuyeReceived(Double amortizePriceWuyeReceived) {
		this.amortizePriceWuyeReceived = amortizePriceWuyeReceived;
	}

	public Double getAmortizePriceGuanliReceived() {
		return amortizePriceGuanliReceived;
	}

	public void setAmortizePriceGuanliReceived(Double amortizePriceGuanliReceived) {
		this.amortizePriceGuanliReceived = amortizePriceGuanliReceived;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public Double getRentArea() {
		return rentArea;
	}

	public void setRentArea(Double rentArea) {
		this.rentArea = rentArea;
	}

	//合同编号
	private String contractCode;
	//商铺编号
	private String code;
	//商铺楼层
	private String floor;
	//房屋类型
	private String roomTypeName;
	//建筑面积
	private Double buildingArea;
	//使用面积
	private Double floorArea;
	//商铺ID
	private Long shopId;
	//当月摊销天数
	private Integer day;
	//每月摊销钱数
	private Double total;
	//未付金额
	private Double unpay;
	//已付金额
	private Double payed;
	//收缴率
	private Double percent;
	//收缴率String
	private String percentString;

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getPercentString() {
		return percentString;
	}

	public void setPercentString(String percentString) {
		this.percentString = percentString;
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
	 * 设置：每天摊销钱数
	 */
	public void setAmortizePrice(Double amortizePrice) {
		this.amortizePrice = amortizePrice;
	}
	/**
	 * 获取：每天摊销钱数
	 */
	public Double getAmortizePrice() {
		return amortizePrice;
	}
	/**
	 * 设置：计算月
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * 获取：计算月
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * 设置：商铺编号
	 */
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	/**
	 * 获取：商铺编号
	 */
	public String getShopCode() {
		return shopCode;
	}
	/**
	 * 设置：商铺ID
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：商铺ID
	 */
	public Long getShopId() {
		return shopId;
	}
	/**
	 * 设置：当月摊销天数
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * 获取：当月摊销天数
	 */
	public Integer getDay() {
		return day;
	}
	/**
	 * 设置：每月摊销钱数
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * 获取：每月摊销钱数
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * 设置：未付金额
	 */
	public void setUnpay(Double unpay) {
		this.unpay = unpay;
	}
	/**
	 * 获取：未付金额
	 */
	public Double getUnpay() {
		return unpay;
	}
	/**
	 * 设置：已付金额
	 */
	public void setPayed(Double payed) {
		this.payed = payed;
	}
	/**
	 * 获取：已付金额
	 */
	public Double getPayed() {
		return payed;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
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

	public Double getAmortizePriceZujin() {
		return amortizePriceZujin;
	}

	public Double getTotalZujinUn() {
		return totalZujinUn;
	}

	public void setTotalZujinUn(Double totalZujinUn) {
		this.totalZujinUn = totalZujinUn;
	}

	public Double getTotalWuyeUn() {
		return totalWuyeUn;
	}

	public void setTotalWuyeUn(Double totalWuyeUn) {
		this.totalWuyeUn = totalWuyeUn;
	}

	public Double getTotalGuanliUn() {
		return totalGuanliUn;
	}

	public void setTotalGuanliUn(Double totalGuanliUn) {
		this.totalGuanliUn = totalGuanliUn;
	}

	public void setAmortizePriceZujin(Double amortizePriceZujin) {
		this.amortizePriceZujin = amortizePriceZujin;
	}

	public Double getAmortizePriceWuye() {
		return amortizePriceWuye;
	}

	public void setAmortizePriceWuye(Double amortizePriceWuye) {
		this.amortizePriceWuye = amortizePriceWuye;
	}

	public Double getAmortizePriceGuanli() {
		return amortizePriceGuanli;
	}

	public void setAmortizePriceGuanli(Double amortizePriceGuanli) {
		this.amortizePriceGuanli = amortizePriceGuanli;
	}

	public Double getTotalZujin13() {
		return totalZujin13;
	}

	public void setTotalZujin13(Double totalZujin13) {
		this.totalZujin13 = totalZujin13;
	}

	public Double getTotalWuye13() {
		return totalWuye13;
	}

	public void setTotalWuye13(Double totalWuye13) {
		this.totalWuye13 = totalWuye13;
	}

	public Double getTotalGuanli13() {
		return totalGuanli13;
	}

	public void setTotalGuanli13(Double totalGuanli13) {
		this.totalGuanli13 = totalGuanli13;
	}
}
