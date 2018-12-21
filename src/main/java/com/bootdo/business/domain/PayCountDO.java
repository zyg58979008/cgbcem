package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 商铺合同缴费管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-19 13:50:07
 */
public class PayCountDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double contractPayTotal1;
	private Double contractPayTotalReceived1;
	private Double contractPayTotalUnreceived1;
	private int rowNum;
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	private int num6;
	private int num7;
	private int num8;
	private int num9;
	private int num10;
	private int num11;
	private int num12;
	private Double contractPayTotal2;
	private Double contractPayTotalReceived2;
	private Double contractPayTotalUnreceived2;

	private Double contractPayTotal3;
	private Double contractPayTotalReceived3;
	private Double contractPayTotalUnreceived3;

	private Double contractPayTotal4;
	private Double contractPayTotalReceived4;
	private Double contractPayTotalUnreceived4;

	private Double contractPayTotal5;
	private Double contractPayTotalReceived5;
	private Double contractPayTotalUnreceived5;

	private Double contractPayTotal6;
	private Double contractPayTotalReceived6;
	private Double contractPayTotalUnreceived6;

	private Double contractPayTotal7;
	private Double contractPayTotalReceived7;
	private Double contractPayTotalUnreceived7;

	private Double contractPayTotal8;
	private Double contractPayTotalReceived8;
	private Double contractPayTotalUnreceived8;

	private Double contractPayTotal9;
	private Double contractPayTotalReceived9;
	private Double contractPayTotalUnreceived9;

	private Double contractPayTotal10;
	private Double contractPayTotalReceived10;
	private Double contractPayTotalUnreceived10;

	private Double contractPayTotal11;
	private Double contractPayTotalReceived11;
	private Double contractPayTotalUnreceived11;

	private Double contractPayTotal12;
	private Double contractPayTotalReceived12;
	private Double contractPayTotalUnreceived12;

	private Double contractPayTotalPart;
	private Double contractPayTotalReceivedPart;
	private Double contractPayTotalUnreceivedPart;

	private Double fenleiGuanliPart;
	private Double fenleiGuanliReceivedPart;
	private Double fenleiGuanliUnreceivedPart;

	private Double fenleiZujinPart;
	private Double fenleiZujinReceivedPart;
	private Double fenleiZujinUnreceivedPart;

	private Double fenleiWuyePart;
	private Double fenleiWuyeReceivedPart;
	private Double fenleiWuyeUnreceivedPart;

	private String startDate1;
	private String endDate1;
	private String startDate2;
	private String endDate2;
	private String startDate3;
	private String endDate3;
	private String startDate4;
	private String endDate4;
	private String startDate5;
	private String endDate5;
	private String startDate6;
	private String endDate6;
	private String startDate7;
	private String endDate7;
	private String startDate8;
	private String endDate8;
	private String startDate9;
	private String endDate9;
	private String startDate10;
	private String endDate10;
	private String startDate11;
	private String endDate11;
	private String startDate12;
	private String endDate12;
	//账户
	private String account;
	//开户行
	private String bank;
	//签约人
	private String contractor;
	//合同实测面积
	private Double contractTrueArea;
	//合同计租面积（含20%公摊）
	private Double contractRentArea;
	//优惠政策
	private String youhui;
	//
	private Long id;
	//合同编号
	private String contractCode;
	//楼层
	private Integer floor;
	//商铺编码
	private String shopCode;
	//计租面积
	private Double rentArea;
	//品牌
	private String brand;
	//开单编码

//唯一ID
	private String oid;
	private String buildingName;

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getNum3() {
		return num3;
	}

	public void setNum3(int num3) {
		this.num3 = num3;
	}

	public int getNum4() {
		return num4;
	}

	public void setNum4(int num4) {
		this.num4 = num4;
	}

	public int getNum5() {
		return num5;
	}

	public void setNum5(int num5) {
		this.num5 = num5;
	}

	public int getNum6() {
		return num6;
	}

	public void setNum6(int num6) {
		this.num6 = num6;
	}

	public int getNum7() {
		return num7;
	}

	public void setNum7(int num7) {
		this.num7 = num7;
	}

	public int getNum8() {
		return num8;
	}

	public void setNum8(int num8) {
		this.num8 = num8;
	}

	public int getNum9() {
		return num9;
	}

	public void setNum9(int num9) {
		this.num9 = num9;
	}

	public int getNum10() {
		return num10;
	}

	public void setNum10(int num10) {
		this.num10 = num10;
	}

	public int getNum11() {
		return num11;
	}

	public void setNum11(int num11) {
		this.num11 = num11;
	}

	public int getNum12() {
		return num12;
	}

	public void setNum12(int num12) {
		this.num12 = num12;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Double getFenleiGuanliPart() {
		return fenleiGuanliPart;
	}

	public void setFenleiGuanliPart(Double fenleiGuanliPart) {
		this.fenleiGuanliPart = fenleiGuanliPart;
	}

	public Double getFenleiGuanliReceivedPart() {
		return fenleiGuanliReceivedPart;
	}

	public void setFenleiGuanliReceivedPart(Double fenleiGuanliReceivedPart) {
		this.fenleiGuanliReceivedPart = fenleiGuanliReceivedPart;
	}

	public Double getFenleiGuanliUnreceivedPart() {
		return fenleiGuanliUnreceivedPart;
	}

	public void setFenleiGuanliUnreceivedPart(Double fenleiGuanliUnreceivedPart) {
		this.fenleiGuanliUnreceivedPart = fenleiGuanliUnreceivedPart;
	}

	public Double getFenleiZujinPart() {
		return fenleiZujinPart;
	}

	public void setFenleiZujinPart(Double fenleiZujinPart) {
		this.fenleiZujinPart = fenleiZujinPart;
	}

	public Double getFenleiZujinReceivedPart() {
		return fenleiZujinReceivedPart;
	}

	public void setFenleiZujinReceivedPart(Double fenleiZujinReceivedPart) {
		this.fenleiZujinReceivedPart = fenleiZujinReceivedPart;
	}

	public Double getFenleiZujinUnreceivedPart() {
		return fenleiZujinUnreceivedPart;
	}

	public void setFenleiZujinUnreceivedPart(Double fenleiZujinUnreceivedPart) {
		this.fenleiZujinUnreceivedPart = fenleiZujinUnreceivedPart;
	}

	public Double getFenleiWuyePart() {
		return fenleiWuyePart;
	}

	public void setFenleiWuyePart(Double fenleiWuyePart) {
		this.fenleiWuyePart = fenleiWuyePart;
	}

	public Double getFenleiWuyeReceivedPart() {
		return fenleiWuyeReceivedPart;
	}

	public void setFenleiWuyeReceivedPart(Double fenleiWuyeReceivedPart) {
		this.fenleiWuyeReceivedPart = fenleiWuyeReceivedPart;
	}

	public Double getFenleiWuyeUnreceivedPart() {
		return fenleiWuyeUnreceivedPart;
	}

	public void setFenleiWuyeUnreceivedPart(Double fenleiWuyeUnreceivedPart) {
		this.fenleiWuyeUnreceivedPart = fenleiWuyeUnreceivedPart;
	}

	public Double getFenleiWuye1() {
		return fenleiWuye1;
	}

	public void setFenleiWuye1(Double fenleiWuye1) {
		this.fenleiWuye1 = fenleiWuye1;
	}

	public Double getFenleiWuyeReceived1() {
		return fenleiWuyeReceived1;
	}

	public void setFenleiWuyeReceived1(Double fenleiWuyeReceived1) {
		this.fenleiWuyeReceived1 = fenleiWuyeReceived1;
	}

	public Double getFenleiWuyeUnreceived1() {
		return fenleiWuyeUnreceived1;
	}

	public void setFenleiWuyeUnreceived1(Double fenleiWuyeUnreceived1) {
		this.fenleiWuyeUnreceived1 = fenleiWuyeUnreceived1;
	}

	public Double getFenleiWuye2() {
		return fenleiWuye2;
	}

	public void setFenleiWuye2(Double fenleiWuye2) {
		this.fenleiWuye2 = fenleiWuye2;
	}

	public Double getFenleiWuyeReceived2() {
		return fenleiWuyeReceived2;
	}

	public void setFenleiWuyeReceived2(Double fenleiWuyeReceived2) {
		this.fenleiWuyeReceived2 = fenleiWuyeReceived2;
	}

	public Double getFenleiWuyeUnreceived2() {
		return fenleiWuyeUnreceived2;
	}

	public void setFenleiWuyeUnreceived2(Double fenleiWuyeUnreceived2) {
		this.fenleiWuyeUnreceived2 = fenleiWuyeUnreceived2;
	}

	public Double getFenleiWuye3() {
		return fenleiWuye3;
	}

	public void setFenleiWuye3(Double fenleiWuye3) {
		this.fenleiWuye3 = fenleiWuye3;
	}

	public Double getFenleiWuyeReceived3() {
		return fenleiWuyeReceived3;
	}

	public void setFenleiWuyeReceived3(Double fenleiWuyeReceived3) {
		this.fenleiWuyeReceived3 = fenleiWuyeReceived3;
	}

	public Double getFenleiWuyeUnreceived3() {
		return fenleiWuyeUnreceived3;
	}

	public void setFenleiWuyeUnreceived3(Double fenleiWuyeUnreceived3) {
		this.fenleiWuyeUnreceived3 = fenleiWuyeUnreceived3;
	}

	public Double getFenleiWuye4() {
		return fenleiWuye4;
	}

	public void setFenleiWuye4(Double fenleiWuye4) {
		this.fenleiWuye4 = fenleiWuye4;
	}

	public Double getFenleiWuyeReceived4() {
		return fenleiWuyeReceived4;
	}

	public void setFenleiWuyeReceived4(Double fenleiWuyeReceived4) {
		this.fenleiWuyeReceived4 = fenleiWuyeReceived4;
	}

	public Double getFenleiWuyeUnreceived4() {
		return fenleiWuyeUnreceived4;
	}

	public void setFenleiWuyeUnreceived4(Double fenleiWuyeUnreceived4) {
		this.fenleiWuyeUnreceived4 = fenleiWuyeUnreceived4;
	}

	public Double getFenleiWuye5() {
		return fenleiWuye5;
	}

	public void setFenleiWuye5(Double fenleiWuye5) {
		this.fenleiWuye5 = fenleiWuye5;
	}

	public Double getFenleiWuyeReceived5() {
		return fenleiWuyeReceived5;
	}

	public void setFenleiWuyeReceived5(Double fenleiWuyeReceived5) {
		this.fenleiWuyeReceived5 = fenleiWuyeReceived5;
	}

	public Double getFenleiWuyeUnreceived5() {
		return fenleiWuyeUnreceived5;
	}

	public void setFenleiWuyeUnreceived5(Double fenleiWuyeUnreceived5) {
		this.fenleiWuyeUnreceived5 = fenleiWuyeUnreceived5;
	}

	public Double getFenleiWuye6() {
		return fenleiWuye6;
	}

	public void setFenleiWuye6(Double fenleiWuye6) {
		this.fenleiWuye6 = fenleiWuye6;
	}

	public Double getFenleiWuyeReceived6() {
		return fenleiWuyeReceived6;
	}

	public void setFenleiWuyeReceived6(Double fenleiWuyeReceived6) {
		this.fenleiWuyeReceived6 = fenleiWuyeReceived6;
	}

	public Double getFenleiWuyeUnreceived6() {
		return fenleiWuyeUnreceived6;
	}

	public void setFenleiWuyeUnreceived6(Double fenleiWuyeUnreceived6) {
		this.fenleiWuyeUnreceived6 = fenleiWuyeUnreceived6;
	}

	public Double getFenleiWuye7() {
		return fenleiWuye7;
	}

	public void setFenleiWuye7(Double fenleiWuye7) {
		this.fenleiWuye7 = fenleiWuye7;
	}

	public Double getFenleiWuyeReceived7() {
		return fenleiWuyeReceived7;
	}

	public void setFenleiWuyeReceived7(Double fenleiWuyeReceived7) {
		this.fenleiWuyeReceived7 = fenleiWuyeReceived7;
	}

	public Double getFenleiWuyeUnreceived7() {
		return fenleiWuyeUnreceived7;
	}

	public void setFenleiWuyeUnreceived7(Double fenleiWuyeUnreceived7) {
		this.fenleiWuyeUnreceived7 = fenleiWuyeUnreceived7;
	}

	public Double getFenleiWuye8() {
		return fenleiWuye8;
	}

	public void setFenleiWuye8(Double fenleiWuye8) {
		this.fenleiWuye8 = fenleiWuye8;
	}

	public Double getFenleiWuyeReceived8() {
		return fenleiWuyeReceived8;
	}

	public void setFenleiWuyeReceived8(Double fenleiWuyeReceived8) {
		this.fenleiWuyeReceived8 = fenleiWuyeReceived8;
	}

	public Double getFenleiWuyeUnreceived8() {
		return fenleiWuyeUnreceived8;
	}

	public void setFenleiWuyeUnreceived8(Double fenleiWuyeUnreceived8) {
		this.fenleiWuyeUnreceived8 = fenleiWuyeUnreceived8;
	}

	public Double getFenleiWuye9() {
		return fenleiWuye9;
	}

	public void setFenleiWuye9(Double fenleiWuye9) {
		this.fenleiWuye9 = fenleiWuye9;
	}

	public Double getFenleiWuyeReceived9() {
		return fenleiWuyeReceived9;
	}

	public void setFenleiWuyeReceived9(Double fenleiWuyeReceived9) {
		this.fenleiWuyeReceived9 = fenleiWuyeReceived9;
	}

	public Double getFenleiWuyeUnreceived9() {
		return fenleiWuyeUnreceived9;
	}

	public void setFenleiWuyeUnreceived9(Double fenleiWuyeUnreceived9) {
		this.fenleiWuyeUnreceived9 = fenleiWuyeUnreceived9;
	}

	public Double getFenleiWuye10() {
		return fenleiWuye10;
	}

	public void setFenleiWuye10(Double fenleiWuye10) {
		this.fenleiWuye10 = fenleiWuye10;
	}

	public Double getFenleiWuyeReceived10() {
		return fenleiWuyeReceived10;
	}

	public void setFenleiWuyeReceived10(Double fenleiWuyeReceived10) {
		this.fenleiWuyeReceived10 = fenleiWuyeReceived10;
	}

	public Double getFenleiWuyeUnreceived10() {
		return fenleiWuyeUnreceived10;
	}

	public void setFenleiWuyeUnreceived10(Double fenleiWuyeUnreceived10) {
		this.fenleiWuyeUnreceived10 = fenleiWuyeUnreceived10;
	}

	public Double getFenleiWuye11() {
		return fenleiWuye11;
	}

	public void setFenleiWuye11(Double fenleiWuye11) {
		this.fenleiWuye11 = fenleiWuye11;
	}

	public Double getFenleiWuyeReceived11() {
		return fenleiWuyeReceived11;
	}

	public void setFenleiWuyeReceived11(Double fenleiWuyeReceived11) {
		this.fenleiWuyeReceived11 = fenleiWuyeReceived11;
	}

	public Double getFenleiWuyeUnreceived11() {
		return fenleiWuyeUnreceived11;
	}

	public void setFenleiWuyeUnreceived11(Double fenleiWuyeUnreceived11) {
		this.fenleiWuyeUnreceived11 = fenleiWuyeUnreceived11;
	}

	public Double getFenleiWuye12() {
		return fenleiWuye12;
	}

	public void setFenleiWuye12(Double fenleiWuye12) {
		this.fenleiWuye12 = fenleiWuye12;
	}

	public Double getFenleiWuyeReceived12() {
		return fenleiWuyeReceived12;
	}

	public void setFenleiWuyeReceived12(Double fenleiWuyeReceived12) {
		this.fenleiWuyeReceived12 = fenleiWuyeReceived12;
	}

	public Double getFenleiWuyeUnreceived12() {
		return fenleiWuyeUnreceived12;
	}

	public void setFenleiWuyeUnreceived12(Double fenleiWuyeUnreceived12) {
		this.fenleiWuyeUnreceived12 = fenleiWuyeUnreceived12;
	}

	public Double getFenleiWuye13() {
		return fenleiWuye13;
	}

	public void setFenleiWuye13(Double fenleiWuye13) {
		this.fenleiWuye13 = fenleiWuye13;
	}

	public Double getFenleiWuyeReceived13() {
		return fenleiWuyeReceived13;
	}

	public void setFenleiWuyeReceived13(Double fenleiWuyeReceived13) {
		this.fenleiWuyeReceived13 = fenleiWuyeReceived13;
	}

	public Double getFenleiWuyeUnreceived13() {
		return fenleiWuyeUnreceived13;
	}

	public void setFenleiWuyeUnreceived13(Double fenleiWuyeUnreceived13) {
		this.fenleiWuyeUnreceived13 = fenleiWuyeUnreceived13;
	}

	public Double getFenleiZujin1() {
		return fenleiZujin1;
	}

	public void setFenleiZujin1(Double fenleiZujin1) {
		this.fenleiZujin1 = fenleiZujin1;
	}

	public Double getFenleiZujinReceived1() {
		return fenleiZujinReceived1;
	}

	public void setFenleiZujinReceived1(Double fenleiZujinReceived1) {
		this.fenleiZujinReceived1 = fenleiZujinReceived1;
	}

	public Double getFenleiZujinUnreceived1() {
		return fenleiZujinUnreceived1;
	}

	public void setFenleiZujinUnreceived1(Double fenleiZujinUnreceived1) {
		this.fenleiZujinUnreceived1 = fenleiZujinUnreceived1;
	}

	public Double getFenleiZujin2() {
		return fenleiZujin2;
	}

	public void setFenleiZujin2(Double fenleiZujin2) {
		this.fenleiZujin2 = fenleiZujin2;
	}

	public Double getFenleiZujinReceived2() {
		return fenleiZujinReceived2;
	}

	public void setFenleiZujinReceived2(Double fenleiZujinReceived2) {
		this.fenleiZujinReceived2 = fenleiZujinReceived2;
	}

	public Double getFenleiZujinUnreceived2() {
		return fenleiZujinUnreceived2;
	}

	public void setFenleiZujinUnreceived2(Double fenleiZujinUnreceived2) {
		this.fenleiZujinUnreceived2 = fenleiZujinUnreceived2;
	}

	public Double getFenleiZujin3() {
		return fenleiZujin3;
	}

	public void setFenleiZujin3(Double fenleiZujin3) {
		this.fenleiZujin3 = fenleiZujin3;
	}

	public Double getFenleiZujinReceived3() {
		return fenleiZujinReceived3;
	}

	public void setFenleiZujinReceived3(Double fenleiZujinReceived3) {
		this.fenleiZujinReceived3 = fenleiZujinReceived3;
	}

	public Double getFenleiZujinUnreceived3() {
		return fenleiZujinUnreceived3;
	}

	public void setFenleiZujinUnreceived3(Double fenleiZujinUnreceived3) {
		this.fenleiZujinUnreceived3 = fenleiZujinUnreceived3;
	}

	public Double getFenleiZujin4() {
		return fenleiZujin4;
	}

	public void setFenleiZujin4(Double fenleiZujin4) {
		this.fenleiZujin4 = fenleiZujin4;
	}

	public Double getFenleiZujinReceived4() {
		return fenleiZujinReceived4;
	}

	public void setFenleiZujinReceived4(Double fenleiZujinReceived4) {
		this.fenleiZujinReceived4 = fenleiZujinReceived4;
	}

	public Double getFenleiZujinUnreceived4() {
		return fenleiZujinUnreceived4;
	}

	public void setFenleiZujinUnreceived4(Double fenleiZujinUnreceived4) {
		this.fenleiZujinUnreceived4 = fenleiZujinUnreceived4;
	}

	public Double getFenleiZujin5() {
		return fenleiZujin5;
	}

	public void setFenleiZujin5(Double fenleiZujin5) {
		this.fenleiZujin5 = fenleiZujin5;
	}

	public Double getFenleiZujinReceived5() {
		return fenleiZujinReceived5;
	}

	public void setFenleiZujinReceived5(Double fenleiZujinReceived5) {
		this.fenleiZujinReceived5 = fenleiZujinReceived5;
	}

	public Double getFenleiZujinUnreceived5() {
		return fenleiZujinUnreceived5;
	}

	public void setFenleiZujinUnreceived5(Double fenleiZujinUnreceived5) {
		this.fenleiZujinUnreceived5 = fenleiZujinUnreceived5;
	}

	public Double getFenleiZujin6() {
		return fenleiZujin6;
	}

	public void setFenleiZujin6(Double fenleiZujin6) {
		this.fenleiZujin6 = fenleiZujin6;
	}

	public Double getFenleiZujinReceived6() {
		return fenleiZujinReceived6;
	}

	public void setFenleiZujinReceived6(Double fenleiZujinReceived6) {
		this.fenleiZujinReceived6 = fenleiZujinReceived6;
	}

	public Double getFenleiZujinUnreceived6() {
		return fenleiZujinUnreceived6;
	}

	public void setFenleiZujinUnreceived6(Double fenleiZujinUnreceived6) {
		this.fenleiZujinUnreceived6 = fenleiZujinUnreceived6;
	}

	public Double getFenleiZujin7() {
		return fenleiZujin7;
	}

	public void setFenleiZujin7(Double fenleiZujin7) {
		this.fenleiZujin7 = fenleiZujin7;
	}

	public Double getFenleiZujinReceived7() {
		return fenleiZujinReceived7;
	}

	public void setFenleiZujinReceived7(Double fenleiZujinReceived7) {
		this.fenleiZujinReceived7 = fenleiZujinReceived7;
	}

	public Double getFenleiZujinUnreceived7() {
		return fenleiZujinUnreceived7;
	}

	public void setFenleiZujinUnreceived7(Double fenleiZujinUnreceived7) {
		this.fenleiZujinUnreceived7 = fenleiZujinUnreceived7;
	}

	public Double getFenleiZujin8() {
		return fenleiZujin8;
	}

	public void setFenleiZujin8(Double fenleiZujin8) {
		this.fenleiZujin8 = fenleiZujin8;
	}

	public Double getFenleiZujinReceived8() {
		return fenleiZujinReceived8;
	}

	public void setFenleiZujinReceived8(Double fenleiZujinReceived8) {
		this.fenleiZujinReceived8 = fenleiZujinReceived8;
	}

	public Double getFenleiZujinUnreceived8() {
		return fenleiZujinUnreceived8;
	}

	public void setFenleiZujinUnreceived8(Double fenleiZujinUnreceived8) {
		this.fenleiZujinUnreceived8 = fenleiZujinUnreceived8;
	}

	public Double getFenleiZujin9() {
		return fenleiZujin9;
	}

	public void setFenleiZujin9(Double fenleiZujin9) {
		this.fenleiZujin9 = fenleiZujin9;
	}

	public Double getFenleiZujinReceived9() {
		return fenleiZujinReceived9;
	}

	public void setFenleiZujinReceived9(Double fenleiZujinReceived9) {
		this.fenleiZujinReceived9 = fenleiZujinReceived9;
	}

	public Double getFenleiZujinUnreceived9() {
		return fenleiZujinUnreceived9;
	}

	public void setFenleiZujinUnreceived9(Double fenleiZujinUnreceived9) {
		this.fenleiZujinUnreceived9 = fenleiZujinUnreceived9;
	}

	public Double getFenleiZujin10() {
		return fenleiZujin10;
	}

	public void setFenleiZujin10(Double fenleiZujin10) {
		this.fenleiZujin10 = fenleiZujin10;
	}

	public Double getFenleiZujinReceived10() {
		return fenleiZujinReceived10;
	}

	public void setFenleiZujinReceived10(Double fenleiZujinReceived10) {
		this.fenleiZujinReceived10 = fenleiZujinReceived10;
	}

	public Double getFenleiZujinUnreceived10() {
		return fenleiZujinUnreceived10;
	}

	public void setFenleiZujinUnreceived10(Double fenleiZujinUnreceived10) {
		this.fenleiZujinUnreceived10 = fenleiZujinUnreceived10;
	}

	public Double getFenleiZujin11() {
		return fenleiZujin11;
	}

	public void setFenleiZujin11(Double fenleiZujin11) {
		this.fenleiZujin11 = fenleiZujin11;
	}

	public Double getFenleiZujinReceived11() {
		return fenleiZujinReceived11;
	}

	public void setFenleiZujinReceived11(Double fenleiZujinReceived11) {
		this.fenleiZujinReceived11 = fenleiZujinReceived11;
	}

	public Double getFenleiZujinUnreceived11() {
		return fenleiZujinUnreceived11;
	}

	public void setFenleiZujinUnreceived11(Double fenleiZujinUnreceived11) {
		this.fenleiZujinUnreceived11 = fenleiZujinUnreceived11;
	}

	public Double getFenleiZujin12() {
		return fenleiZujin12;
	}

	public void setFenleiZujin12(Double fenleiZujin12) {
		this.fenleiZujin12 = fenleiZujin12;
	}

	public Double getFenleiZujinReceived12() {
		return fenleiZujinReceived12;
	}

	public void setFenleiZujinReceived12(Double fenleiZujinReceived12) {
		this.fenleiZujinReceived12 = fenleiZujinReceived12;
	}

	public Double getFenleiZujinUnreceived12() {
		return fenleiZujinUnreceived12;
	}

	public void setFenleiZujinUnreceived12(Double fenleiZujinUnreceived12) {
		this.fenleiZujinUnreceived12 = fenleiZujinUnreceived12;
	}

	public Double getFenleiZujin13() {
		return fenleiZujin13;
	}

	public void setFenleiZujin13(Double fenleiZujin13) {
		this.fenleiZujin13 = fenleiZujin13;
	}

	public Double getFenleiZujinReceived13() {
		return fenleiZujinReceived13;
	}

	public void setFenleiZujinReceived13(Double fenleiZujinReceived13) {
		this.fenleiZujinReceived13 = fenleiZujinReceived13;
	}

	public Double getFenleiZujinUnreceived13() {
		return fenleiZujinUnreceived13;
	}

	public void setFenleiZujinUnreceived13(Double fenleiZujinUnreceived13) {
		this.fenleiZujinUnreceived13 = fenleiZujinUnreceived13;
	}

	public Double getFenleiGuanli1() {
		return fenleiGuanli1;
	}

	public void setFenleiGuanli1(Double fenleiGuanli1) {
		this.fenleiGuanli1 = fenleiGuanli1;
	}

	public Double getFenleiGuanliReceived1() {
		return fenleiGuanliReceived1;
	}

	public void setFenleiGuanliReceived1(Double fenleiGuanliReceived1) {
		this.fenleiGuanliReceived1 = fenleiGuanliReceived1;
	}

	public Double getFenleiGuanliUnreceived1() {
		return fenleiGuanliUnreceived1;
	}

	public void setFenleiGuanliUnreceived1(Double fenleiGuanliUnreceived1) {
		this.fenleiGuanliUnreceived1 = fenleiGuanliUnreceived1;
	}

	public Double getFenleiGuanli2() {
		return fenleiGuanli2;
	}

	public void setFenleiGuanli2(Double fenleiGuanli2) {
		this.fenleiGuanli2 = fenleiGuanli2;
	}

	public Double getFenleiGuanliReceived2() {
		return fenleiGuanliReceived2;
	}

	public void setFenleiGuanliReceived2(Double fenleiGuanliReceived2) {
		this.fenleiGuanliReceived2 = fenleiGuanliReceived2;
	}

	public Double getFenleiGuanliUnreceived2() {
		return fenleiGuanliUnreceived2;
	}

	public void setFenleiGuanliUnreceived2(Double fenleiGuanliUnreceived2) {
		this.fenleiGuanliUnreceived2 = fenleiGuanliUnreceived2;
	}

	public Double getFenleiGuanli3() {
		return fenleiGuanli3;
	}

	public void setFenleiGuanli3(Double fenleiGuanli3) {
		this.fenleiGuanli3 = fenleiGuanli3;
	}

	public Double getFenleiGuanliReceived3() {
		return fenleiGuanliReceived3;
	}

	public void setFenleiGuanliReceived3(Double fenleiGuanliReceived3) {
		this.fenleiGuanliReceived3 = fenleiGuanliReceived3;
	}

	public Double getFenleiGuanliUnreceived3() {
		return fenleiGuanliUnreceived3;
	}

	public void setFenleiGuanliUnreceived3(Double fenleiGuanliUnreceived3) {
		this.fenleiGuanliUnreceived3 = fenleiGuanliUnreceived3;
	}

	public Double getFenleiGuanli4() {
		return fenleiGuanli4;
	}

	public void setFenleiGuanli4(Double fenleiGuanli4) {
		this.fenleiGuanli4 = fenleiGuanli4;
	}

	public Double getFenleiGuanliReceived4() {
		return fenleiGuanliReceived4;
	}

	public void setFenleiGuanliReceived4(Double fenleiGuanliReceived4) {
		this.fenleiGuanliReceived4 = fenleiGuanliReceived4;
	}

	public Double getFenleiGuanliUnreceived4() {
		return fenleiGuanliUnreceived4;
	}

	public void setFenleiGuanliUnreceived4(Double fenleiGuanliUnreceived4) {
		this.fenleiGuanliUnreceived4 = fenleiGuanliUnreceived4;
	}

	public Double getFenleiGuanli5() {
		return fenleiGuanli5;
	}

	public void setFenleiGuanli5(Double fenleiGuanli5) {
		this.fenleiGuanli5 = fenleiGuanli5;
	}

	public Double getFenleiGuanliReceived5() {
		return fenleiGuanliReceived5;
	}

	public void setFenleiGuanliReceived5(Double fenleiGuanliReceived5) {
		this.fenleiGuanliReceived5 = fenleiGuanliReceived5;
	}

	public Double getFenleiGuanliUnreceived5() {
		return fenleiGuanliUnreceived5;
	}

	public void setFenleiGuanliUnreceived5(Double fenleiGuanliUnreceived5) {
		this.fenleiGuanliUnreceived5 = fenleiGuanliUnreceived5;
	}

	public Double getFenleiGuanli6() {
		return fenleiGuanli6;
	}

	public void setFenleiGuanli6(Double fenleiGuanli6) {
		this.fenleiGuanli6 = fenleiGuanli6;
	}

	public Double getFenleiGuanliReceived6() {
		return fenleiGuanliReceived6;
	}

	public void setFenleiGuanliReceived6(Double fenleiGuanliReceived6) {
		this.fenleiGuanliReceived6 = fenleiGuanliReceived6;
	}

	public Double getFenleiGuanliUnreceived6() {
		return fenleiGuanliUnreceived6;
	}

	public void setFenleiGuanliUnreceived6(Double fenleiGuanliUnreceived6) {
		this.fenleiGuanliUnreceived6 = fenleiGuanliUnreceived6;
	}

	public Double getFenleiGuanli7() {
		return fenleiGuanli7;
	}

	public void setFenleiGuanli7(Double fenleiGuanli7) {
		this.fenleiGuanli7 = fenleiGuanli7;
	}

	public Double getFenleiGuanliReceived7() {
		return fenleiGuanliReceived7;
	}

	public void setFenleiGuanliReceived7(Double fenleiGuanliReceived7) {
		this.fenleiGuanliReceived7 = fenleiGuanliReceived7;
	}

	public Double getFenleiGuanliUnreceived7() {
		return fenleiGuanliUnreceived7;
	}

	public void setFenleiGuanliUnreceived7(Double fenleiGuanliUnreceived7) {
		this.fenleiGuanliUnreceived7 = fenleiGuanliUnreceived7;
	}

	public Double getFenleiGuanli8() {
		return fenleiGuanli8;
	}

	public void setFenleiGuanli8(Double fenleiGuanli8) {
		this.fenleiGuanli8 = fenleiGuanli8;
	}

	public Double getFenleiGuanliReceived8() {
		return fenleiGuanliReceived8;
	}

	public void setFenleiGuanliReceived8(Double fenleiGuanliReceived8) {
		this.fenleiGuanliReceived8 = fenleiGuanliReceived8;
	}

	public Double getFenleiGuanliUnreceived8() {
		return fenleiGuanliUnreceived8;
	}

	public void setFenleiGuanliUnreceived8(Double fenleiGuanliUnreceived8) {
		this.fenleiGuanliUnreceived8 = fenleiGuanliUnreceived8;
	}

	public Double getFenleiGuanli9() {
		return fenleiGuanli9;
	}

	public void setFenleiGuanli9(Double fenleiGuanli9) {
		this.fenleiGuanli9 = fenleiGuanli9;
	}

	public Double getFenleiGuanliReceived9() {
		return fenleiGuanliReceived9;
	}

	public void setFenleiGuanliReceived9(Double fenleiGuanliReceived9) {
		this.fenleiGuanliReceived9 = fenleiGuanliReceived9;
	}

	public Double getFenleiGuanliUnreceived9() {
		return fenleiGuanliUnreceived9;
	}

	public void setFenleiGuanliUnreceived9(Double fenleiGuanliUnreceived9) {
		this.fenleiGuanliUnreceived9 = fenleiGuanliUnreceived9;
	}

	public Double getFenleiGuanli10() {
		return fenleiGuanli10;
	}

	public void setFenleiGuanli10(Double fenleiGuanli10) {
		this.fenleiGuanli10 = fenleiGuanli10;
	}

	public Double getFenleiGuanliReceived10() {
		return fenleiGuanliReceived10;
	}

	public void setFenleiGuanliReceived10(Double fenleiGuanliReceived10) {
		this.fenleiGuanliReceived10 = fenleiGuanliReceived10;
	}

	public Double getFenleiGuanliUnreceived10() {
		return fenleiGuanliUnreceived10;
	}

	public void setFenleiGuanliUnreceived10(Double fenleiGuanliUnreceived10) {
		this.fenleiGuanliUnreceived10 = fenleiGuanliUnreceived10;
	}

	public Double getFenleiGuanli11() {
		return fenleiGuanli11;
	}

	public void setFenleiGuanli11(Double fenleiGuanli11) {
		this.fenleiGuanli11 = fenleiGuanli11;
	}

	public Double getFenleiGuanliReceived11() {
		return fenleiGuanliReceived11;
	}

	public void setFenleiGuanliReceived11(Double fenleiGuanliReceived11) {
		this.fenleiGuanliReceived11 = fenleiGuanliReceived11;
	}

	public Double getFenleiGuanliUnreceived11() {
		return fenleiGuanliUnreceived11;
	}

	public void setFenleiGuanliUnreceived11(Double fenleiGuanliUnreceived11) {
		this.fenleiGuanliUnreceived11 = fenleiGuanliUnreceived11;
	}

	public Double getFenleiGuanli12() {
		return fenleiGuanli12;
	}

	public void setFenleiGuanli12(Double fenleiGuanli12) {
		this.fenleiGuanli12 = fenleiGuanli12;
	}

	public Double getFenleiGuanliReceived12() {
		return fenleiGuanliReceived12;
	}

	public void setFenleiGuanliReceived12(Double fenleiGuanliReceived12) {
		this.fenleiGuanliReceived12 = fenleiGuanliReceived12;
	}

	public Double getFenleiGuanliUnreceived12() {
		return fenleiGuanliUnreceived12;
	}

	public void setFenleiGuanliUnreceived12(Double fenleiGuanliUnreceived12) {
		this.fenleiGuanliUnreceived12 = fenleiGuanliUnreceived12;
	}

	public Double getFenleiGuanli13() {
		return fenleiGuanli13;
	}

	public void setFenleiGuanli13(Double fenleiGuanli13) {
		this.fenleiGuanli13 = fenleiGuanli13;
	}

	public Double getFenleiGuanliReceived13() {
		return fenleiGuanliReceived13;
	}

	public void setFenleiGuanliReceived13(Double fenleiGuanliReceived13) {
		this.fenleiGuanliReceived13 = fenleiGuanliReceived13;
	}

	public Double getFenleiGuanliUnreceived13() {
		return fenleiGuanliUnreceived13;
	}

	public void setFenleiGuanliUnreceived13(Double fenleiGuanliUnreceived13) {
		this.fenleiGuanliUnreceived13 = fenleiGuanliUnreceived13;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public Double getContractTrueArea() {
		return contractTrueArea;
	}

	public void setContractTrueArea(Double contractTrueArea) {
		this.contractTrueArea = contractTrueArea;
	}

	public Double getContractRentArea() {
		return contractRentArea;
	}

	public void setContractRentArea(Double contractRentArea) {
		this.contractRentArea = contractRentArea;
	}

	public String getYouhui() {
		return youhui;
	}

	public void setYouhui(String youhui) {
		this.youhui = youhui;
	}

	public String getKaidanCode() {
		return kaidanCode;
	}

	public void setKaidanCode(String kaidanCode) {
		this.kaidanCode = kaidanCode;
	}

	public String getKaidanName() {
		return kaidanName;
	}

	public void setKaidanName(String kaidanName) {
		this.kaidanName = kaidanName;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	private String kaidanCode;
	//开单名称
	private String kaidanName;
	//收款人
	private String payee;
	//签约日期
	private Date contractDate;
	//合同起
	private String contractStartDateString;
	//合同止
	private String contractEndDateString;
	//合同起
	private Date contractStartDate;
	//身份证号
	private String idCard;
	//合同止
	private Date contractEndDate;
	//合同期应收租金
	private Double contractPayTotal;
	//合同期已收租金
	private Double contractPayTotalReceived;
	//合同期未收租金
	private Double contractPayTotalUnreceived;
	//租金单价
	private Double unitPrice;
	//收款方式
	private String payType;
	//收入分类-租金
	private Double fenleiZujin;
	//收入分类-租金-已收
	private Double fenleiZujinReceived;
	//收入分类-租金-未收
	private Double fenleiZujinUnreceived;
	//收入分类-物业费
	private Double fenleiWuye;
	//收入分类-物业费_已收
	private Double fenleiWuyeReceived;
	//收入分类-物业费_未收
	private Double fenleiWuyeUnreceived;
	//收入分类-管理费
	private Double fenleiGuanli;
	//收入分类-管理费-已收
	private Double fenleiGuanliReceived;
	//收入分类-管理费-未收
	private Double fenleiGuanliUnreceived;
	//计费起日期
	private Date countStartDate;
	//计费止日期
	private Date countEndDate;
	//计费起日期
	private String countStartDateString;
	//计费止日期
	private String countEndDateString;
	//摊销月数
	private Integer amortizeMonths;
	//应收履约保证金
	private Double receivableLvyue;
	//应收质量保证金
	private Double receivableZhiliang;
	//应收服务保证金
	private Double receivableFuwu;
	//应收装修押金
	private Double receivableZhuangxiu;
	//已收履约保证金
	private Double receivedLvyue;
	//已收质量保证金
	private Double receivedZhiliang;
	//已收服务保证金
	private Double receivedFuwu;
	//已收装修保证金
	private Double receivedZhuangxiu;
	//差额
	private Double discrepancy;
	//
	//优惠后租金单价
	private Double youhuiUnitPrice;
	//每天摊销钱数
	private Double amortizePrice;
	//折扣
	private Double discount;
	//楼宇ID
	private Long buildingId;
	//合同状态：0-生效；1-待生效；2-撤场
	private String state;
	//
	private Double fenleiWuye1;
	private Double fenleiWuyeReceived1;
	private Double fenleiWuyeUnreceived1;

	private Double fenleiWuye2;
	private Double fenleiWuyeReceived2;
	private Double fenleiWuyeUnreceived2;

	private Double fenleiWuye3;
	private Double fenleiWuyeReceived3;
	private Double fenleiWuyeUnreceived3;

	private Double fenleiWuye4;
	private Double fenleiWuyeReceived4;
	private Double fenleiWuyeUnreceived4;

	private Double fenleiWuye5;
	private Double fenleiWuyeReceived5;
	private Double fenleiWuyeUnreceived5;

	private Double fenleiWuye6;
	private Double fenleiWuyeReceived6;
	private Double fenleiWuyeUnreceived6;

	private Double fenleiWuye7;
	private Double fenleiWuyeReceived7;
	private Double fenleiWuyeUnreceived7;

	private Double fenleiWuye8;
	private Double fenleiWuyeReceived8;
	private Double fenleiWuyeUnreceived8;

	private Double fenleiWuye9;
	private Double fenleiWuyeReceived9;
	private Double fenleiWuyeUnreceived9;

	private Double fenleiWuye10;
	private Double fenleiWuyeReceived10;
	private Double fenleiWuyeUnreceived10;

	private Double fenleiWuye11;
	private Double fenleiWuyeReceived11;
	private Double fenleiWuyeUnreceived11;

	private Double fenleiWuye12;
	private Double fenleiWuyeReceived12;
	private Double fenleiWuyeUnreceived12;

	private Double fenleiWuye13;
	private Double fenleiWuyeReceived13;
	private Double fenleiWuyeUnreceived13;

	private Double fenleiZujin1;
	private Double fenleiZujinReceived1;
	private Double fenleiZujinUnreceived1;

	private Double fenleiZujin2;
	private Double fenleiZujinReceived2;
	private Double fenleiZujinUnreceived2;

	private Double fenleiZujin3;
	private Double fenleiZujinReceived3;
	private Double fenleiZujinUnreceived3;

	private Double fenleiZujin4;
	private Double fenleiZujinReceived4;
	private Double fenleiZujinUnreceived4;

	private Double fenleiZujin5;
	private Double fenleiZujinReceived5;
	private Double fenleiZujinUnreceived5;

	private Double fenleiZujin6;
	private Double fenleiZujinReceived6;
	private Double fenleiZujinUnreceived6;

	private Double fenleiZujin7;
	private Double fenleiZujinReceived7;
	private Double fenleiZujinUnreceived7;

	private Double fenleiZujin8;
	private Double fenleiZujinReceived8;
	private Double fenleiZujinUnreceived8;

	private Double fenleiZujin9;
	private Double fenleiZujinReceived9;
	private Double fenleiZujinUnreceived9;

	private Double fenleiZujin10;
	private Double fenleiZujinReceived10;
	private Double fenleiZujinUnreceived10;

	private Double fenleiZujin11;
	private Double fenleiZujinReceived11;
	private Double fenleiZujinUnreceived11;

	private Double fenleiZujin12;
	private Double fenleiZujinReceived12;
	private Double fenleiZujinUnreceived12;

	private Double fenleiZujin13;
	private Double fenleiZujinReceived13;
	private Double fenleiZujinUnreceived13;

	private Double fenleiGuanli1;
	private Double fenleiGuanliReceived1;
	private Double fenleiGuanliUnreceived1;

	private Double fenleiGuanli2;
	private Double fenleiGuanliReceived2;
	private Double fenleiGuanliUnreceived2;

	private Double fenleiGuanli3;
	private Double fenleiGuanliReceived3;
	private Double fenleiGuanliUnreceived3;

	private Double fenleiGuanli4;
	private Double fenleiGuanliReceived4;
	private Double fenleiGuanliUnreceived4;

	private Double fenleiGuanli5;
	private Double fenleiGuanliReceived5;
	private Double fenleiGuanliUnreceived5;

	private Double fenleiGuanli6;
	private Double fenleiGuanliReceived6;
	private Double fenleiGuanliUnreceived6;

	private Double fenleiGuanli7;
	private Double fenleiGuanliReceived7;
	private Double fenleiGuanliUnreceived7;

	private Double fenleiGuanli8;
	private Double fenleiGuanliReceived8;
	private Double fenleiGuanliUnreceived8;

	private Double fenleiGuanli9;
	private Double fenleiGuanliReceived9;
	private Double fenleiGuanliUnreceived9;

	private Double fenleiGuanli10;
	private Double fenleiGuanliReceived10;
	private Double fenleiGuanliUnreceived10;

	private Double fenleiGuanli11;
	private Double fenleiGuanliReceived11;
	private Double fenleiGuanliUnreceived11;

	private Double fenleiGuanli12;
	private Double fenleiGuanliReceived12;
	private Double fenleiGuanliUnreceived12;

	private Double fenleiGuanli13;
	private Double fenleiGuanliReceived13;
	private Double fenleiGuanliUnreceived13;

	private String unitCode;

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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
	 * 设置：合同编号
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * 获取：合同编号
	 */
	public String getContractCode() {
		return contractCode;
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
	 * 设置：合同期应收租金
	 */
	public void setContractPayTotal(Double contractPayTotal) {
		this.contractPayTotal = contractPayTotal;
	}
	/**
	 * 获取：合同期应收租金
	 */
	public Double getContractPayTotal() {
		return contractPayTotal;
	}
	/**
	 * 设置：合同期已收租金
	 */
	public void setContractPayTotalReceived(Double contractPayTotalReceived) {
		this.contractPayTotalReceived = contractPayTotalReceived;
	}
	/**
	 * 获取：合同期已收租金
	 */
	public Double getContractPayTotalReceived() {
		return contractPayTotalReceived;
	}
	/**
	 * 设置：合同期未收租金
	 */
	public void setContractPayTotalUnreceived(Double contractPayTotalUnreceived) {
		this.contractPayTotalUnreceived = contractPayTotalUnreceived;
	}
	/**
	 * 获取：合同期未收租金
	 */
	public Double getContractPayTotalUnreceived() {
		return contractPayTotalUnreceived;
	}
	/**
	 * 设置：租金单价
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * 获取：租金单价
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * 设置：收款方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：收款方式
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：收入分类-租金
	 */
	public void setFenleiZujin(Double fenleiZujin) {
		this.fenleiZujin = fenleiZujin;
	}
	/**
	 * 获取：收入分类-租金
	 */
	public Double getFenleiZujin() {
		return fenleiZujin;
	}
	/**
	 * 设置：收入分类-租金-已收
	 */
	public void setFenleiZujinReceived(Double fenleiZujinReceived) {
		this.fenleiZujinReceived = fenleiZujinReceived;
	}
	/**
	 * 获取：收入分类-租金-已收
	 */
	public Double getFenleiZujinReceived() {
		return fenleiZujinReceived;
	}
	/**
	 * 设置：收入分类-租金-未收
	 */
	public void setFenleiZujinUnreceived(Double fenleiZujinUnreceived) {
		this.fenleiZujinUnreceived = fenleiZujinUnreceived;
	}
	/**
	 * 获取：收入分类-租金-未收
	 */
	public Double getFenleiZujinUnreceived() {
		return fenleiZujinUnreceived;
	}
	/**
	 * 设置：收入分类-物业费
	 */
	public void setFenleiWuye(Double fenleiWuye) {
		this.fenleiWuye = fenleiWuye;
	}
	/**
	 * 获取：收入分类-物业费
	 */
	public Double getFenleiWuye() {
		return fenleiWuye;
	}
	/**
	 * 设置：收入分类-物业费_已收
	 */
	public void setFenleiWuyeReceived(Double fenleiWuyeReceived) {
		this.fenleiWuyeReceived = fenleiWuyeReceived;
	}
	/**
	 * 获取：收入分类-物业费_已收
	 */
	public Double getFenleiWuyeReceived() {
		return fenleiWuyeReceived;
	}
	/**
	 * 设置：收入分类-物业费_未收
	 */
	public void setFenleiWuyeUnreceived(Double fenleiWuyeUnreceived) {
		this.fenleiWuyeUnreceived = fenleiWuyeUnreceived;
	}
	/**
	 * 获取：收入分类-物业费_未收
	 */
	public Double getFenleiWuyeUnreceived() {
		return fenleiWuyeUnreceived;
	}
	/**
	 * 设置：收入分类-管理费
	 */
	public void setFenleiGuanli(Double fenleiGuanli) {
		this.fenleiGuanli = fenleiGuanli;
	}
	/**
	 * 获取：收入分类-管理费
	 */
	public Double getFenleiGuanli() {
		return fenleiGuanli;
	}
	/**
	 * 设置：收入分类-管理费-已收
	 */
	public void setFenleiGuanliReceived(Double fenleiGuanliReceived) {
		this.fenleiGuanliReceived = fenleiGuanliReceived;
	}
	/**
	 * 获取：收入分类-管理费-已收
	 */
	public Double getFenleiGuanliReceived() {
		return fenleiGuanliReceived;
	}
	/**
	 * 设置：收入分类-管理费-未收
	 */
	public void setFenleiGuanliUnreceived(Double fenleiGuanliUnreceived) {
		this.fenleiGuanliUnreceived = fenleiGuanliUnreceived;
	}
	/**
	 * 获取：收入分类-管理费-未收
	 */
	public Double getFenleiGuanliUnreceived() {
		return fenleiGuanliUnreceived;
	}
	/**
	 * 设置：计费起日期
	 */
	public void setCountStartDate(Date countStartDate) {
		this.countStartDate = countStartDate;
	}
	/**
	 * 获取：计费起日期
	 */
	public Date getCountStartDate() {
		return countStartDate;
	}
	/**
	 * 设置：计费止日期
	 */
	public void setCountEndDate(Date countEndDate) {
		this.countEndDate = countEndDate;
	}
	/**
	 * 获取：计费止日期
	 */
	public Date getCountEndDate() {
		return countEndDate;
	}
	/**
	 * 设置：摊销月数
	 */
	public void setAmortizeMonths(Integer amortizeMonths) {
		this.amortizeMonths = amortizeMonths;
	}
	/**
	 * 获取：摊销月数
	 */
	public Integer getAmortizeMonths() {
		return amortizeMonths;
	}
	/**
	 * 设置：应收履约保证金
	 */
	public void setReceivableLvyue(Double receivableLvyue) {
		this.receivableLvyue = receivableLvyue;
	}
	/**
	 * 获取：应收履约保证金
	 */
	public Double getReceivableLvyue() {
		return receivableLvyue;
	}
	/**
	 * 设置：应收质量保证金
	 */
	public void setReceivableZhiliang(Double receivableZhiliang) {
		this.receivableZhiliang = receivableZhiliang;
	}
	/**
	 * 获取：应收质量保证金
	 */
	public Double getReceivableZhiliang() {
		return receivableZhiliang;
	}
	/**
	 * 设置：应收服务保证金
	 */
	public void setReceivableFuwu(Double receivableFuwu) {
		this.receivableFuwu = receivableFuwu;
	}
	/**
	 * 获取：应收服务保证金
	 */
	public Double getReceivableFuwu() {
		return receivableFuwu;
	}
	/**
	 * 设置：应收装修押金
	 */
	public void setReceivableZhuangxiu(Double receivableZhuangxiu) {
		this.receivableZhuangxiu = receivableZhuangxiu;
	}
	/**
	 * 获取：应收装修押金
	 */
	public Double getReceivableZhuangxiu() {
		return receivableZhuangxiu;
	}
	/**
	 * 设置：已收履约保证金
	 */
	public void setReceivedLvyue(Double receivedLvyue) {
		this.receivedLvyue = receivedLvyue;
	}
	/**
	 * 获取：已收履约保证金
	 */
	public Double getReceivedLvyue() {
		return receivedLvyue;
	}
	/**
	 * 设置：已收质量保证金
	 */
	public void setReceivedZhiliang(Double receivedZhiliang) {
		this.receivedZhiliang = receivedZhiliang;
	}
	/**
	 * 获取：已收质量保证金
	 */
	public Double getReceivedZhiliang() {
		return receivedZhiliang;
	}
	/**
	 * 设置：已收服务保证金
	 */
	public void setReceivedFuwu(Double receivedFuwu) {
		this.receivedFuwu = receivedFuwu;
	}
	/**
	 * 获取：已收服务保证金
	 */
	public Double getReceivedFuwu() {
		return receivedFuwu;
	}
	/**
	 * 设置：已收装修保证金
	 */
	public void setReceivedZhuangxiu(Double receivedZhuangxiu) {
		this.receivedZhuangxiu = receivedZhuangxiu;
	}
	/**
	 * 获取：已收装修保证金
	 */
	public Double getReceivedZhuangxiu() {
		return receivedZhuangxiu;
	}
	/**
	 * 设置：差额
	 */
	public void setDiscrepancy(Double discrepancy) {
		this.discrepancy = discrepancy;
	}
	/**
	 * 获取：差额
	 */
	public Double getDiscrepancy() {
		return discrepancy;
	}
	/**
	 * 设置：优惠后租金单价
	 */
	public void setYouhuiUnitPrice(Double youhuiUnitPrice) {
		this.youhuiUnitPrice = youhuiUnitPrice;
	}
	/**
	 * 获取：优惠后租金单价
	 */
	public Double getYouhuiUnitPrice() {
		return youhuiUnitPrice;
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
	 * 设置：折扣
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/**
	 * 获取：折扣
	 */
	public Double getDiscount() {
		return discount;
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
	 * 设置：合同状态：0-生效；1-待生效；2-撤场
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：合同状态：0-生效；1-待生效；2-撤场
	 */
	public String getState() {
		return state;
	}

	public String getCountStartDateString() {
		return countStartDateString;
	}

	public void setCountStartDateString(String countStartDateString) {
		this.countStartDateString = countStartDateString;
	}

	public String getCountEndDateString() {
		return countEndDateString;
	}

	public void setCountEndDateString(String countEndDateString) {
		this.countEndDateString = countEndDateString;
	}

	public String getContractStartDateString() {
		return contractStartDateString;
	}

	public void setContractStartDateString(String contractStartDateString) {
		this.contractStartDateString = contractStartDateString;
	}

	public String getContractEndDateString() {
		return contractEndDateString;
	}

	public void setContractEndDateString(String contractEndDateString) {
		this.contractEndDateString = contractEndDateString;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Double getContractPayTotal1() {
		return contractPayTotal1;
	}

	public void setContractPayTotal1(Double contractPayTotal1) {
		this.contractPayTotal1 = contractPayTotal1;
	}

	public Double getContractPayTotalReceived1() {
		return contractPayTotalReceived1;
	}

	public void setContractPayTotalReceived1(Double contractPayTotalReceived1) {
		this.contractPayTotalReceived1 = contractPayTotalReceived1;
	}

	public Double getContractPayTotalUnreceived1() {
		return contractPayTotalUnreceived1;
	}

	public void setContractPayTotalUnreceived1(Double contractPayTotalUnreceived1) {
		this.contractPayTotalUnreceived1 = contractPayTotalUnreceived1;
	}

	public Double getContractPayTotal2() {
		return contractPayTotal2;
	}

	public void setContractPayTotal2(Double contractPayTotal2) {
		this.contractPayTotal2 = contractPayTotal2;
	}

	public Double getContractPayTotalReceived2() {
		return contractPayTotalReceived2;
	}

	public void setContractPayTotalReceived2(Double contractPayTotalReceived2) {
		this.contractPayTotalReceived2 = contractPayTotalReceived2;
	}

	public Double getContractPayTotalUnreceived2() {
		return contractPayTotalUnreceived2;
	}

	public void setContractPayTotalUnreceived2(Double contractPayTotalUnreceived2) {
		this.contractPayTotalUnreceived2 = contractPayTotalUnreceived2;
	}

	public Double getContractPayTotal3() {
		return contractPayTotal3;
	}

	public void setContractPayTotal3(Double contractPayTotal3) {
		this.contractPayTotal3 = contractPayTotal3;
	}

	public Double getContractPayTotalReceived3() {
		return contractPayTotalReceived3;
	}

	public void setContractPayTotalReceived3(Double contractPayTotalReceived3) {
		this.contractPayTotalReceived3 = contractPayTotalReceived3;
	}

	public Double getContractPayTotalUnreceived3() {
		return contractPayTotalUnreceived3;
	}

	public void setContractPayTotalUnreceived3(Double contractPayTotalUnreceived3) {
		this.contractPayTotalUnreceived3 = contractPayTotalUnreceived3;
	}

	public Double getContractPayTotal4() {
		return contractPayTotal4;
	}

	public void setContractPayTotal4(Double contractPayTotal4) {
		this.contractPayTotal4 = contractPayTotal4;
	}

	public Double getContractPayTotalReceived4() {
		return contractPayTotalReceived4;
	}

	public void setContractPayTotalReceived4(Double contractPayTotalReceived4) {
		this.contractPayTotalReceived4 = contractPayTotalReceived4;
	}

	public Double getContractPayTotalUnreceived4() {
		return contractPayTotalUnreceived4;
	}

	public void setContractPayTotalUnreceived4(Double contractPayTotalUnreceived4) {
		this.contractPayTotalUnreceived4 = contractPayTotalUnreceived4;
	}

	public Double getContractPayTotal5() {
		return contractPayTotal5;
	}

	public void setContractPayTotal5(Double contractPayTotal5) {
		this.contractPayTotal5 = contractPayTotal5;
	}

	public Double getContractPayTotalReceived5() {
		return contractPayTotalReceived5;
	}

	public void setContractPayTotalReceived5(Double contractPayTotalReceived5) {
		this.contractPayTotalReceived5 = contractPayTotalReceived5;
	}

	public Double getContractPayTotalUnreceived5() {
		return contractPayTotalUnreceived5;
	}

	public void setContractPayTotalUnreceived5(Double contractPayTotalUnreceived5) {
		this.contractPayTotalUnreceived5 = contractPayTotalUnreceived5;
	}

	public Double getContractPayTotal6() {
		return contractPayTotal6;
	}

	public void setContractPayTotal6(Double contractPayTotal6) {
		this.contractPayTotal6 = contractPayTotal6;
	}

	public Double getContractPayTotalReceived6() {
		return contractPayTotalReceived6;
	}

	public void setContractPayTotalReceived6(Double contractPayTotalReceived6) {
		this.contractPayTotalReceived6 = contractPayTotalReceived6;
	}

	public Double getContractPayTotalUnreceived6() {
		return contractPayTotalUnreceived6;
	}

	public void setContractPayTotalUnreceived6(Double contractPayTotalUnreceived6) {
		this.contractPayTotalUnreceived6 = contractPayTotalUnreceived6;
	}

	public Double getContractPayTotal7() {
		return contractPayTotal7;
	}

	public void setContractPayTotal7(Double contractPayTotal7) {
		this.contractPayTotal7 = contractPayTotal7;
	}

	public Double getContractPayTotalReceived7() {
		return contractPayTotalReceived7;
	}

	public void setContractPayTotalReceived7(Double contractPayTotalReceived7) {
		this.contractPayTotalReceived7 = contractPayTotalReceived7;
	}

	public Double getContractPayTotalUnreceived7() {
		return contractPayTotalUnreceived7;
	}

	public void setContractPayTotalUnreceived7(Double contractPayTotalUnreceived7) {
		this.contractPayTotalUnreceived7 = contractPayTotalUnreceived7;
	}

	public Double getContractPayTotal8() {
		return contractPayTotal8;
	}

	public void setContractPayTotal8(Double contractPayTotal8) {
		this.contractPayTotal8 = contractPayTotal8;
	}

	public Double getContractPayTotalReceived8() {
		return contractPayTotalReceived8;
	}

	public void setContractPayTotalReceived8(Double contractPayTotalReceived8) {
		this.contractPayTotalReceived8 = contractPayTotalReceived8;
	}

	public Double getContractPayTotalUnreceived8() {
		return contractPayTotalUnreceived8;
	}

	public void setContractPayTotalUnreceived8(Double contractPayTotalUnreceived8) {
		this.contractPayTotalUnreceived8 = contractPayTotalUnreceived8;
	}

	public Double getContractPayTotal9() {
		return contractPayTotal9;
	}

	public void setContractPayTotal9(Double contractPayTotal9) {
		this.contractPayTotal9 = contractPayTotal9;
	}

	public Double getContractPayTotalReceived9() {
		return contractPayTotalReceived9;
	}

	public void setContractPayTotalReceived9(Double contractPayTotalReceived9) {
		this.contractPayTotalReceived9 = contractPayTotalReceived9;
	}

	public Double getContractPayTotalUnreceived9() {
		return contractPayTotalUnreceived9;
	}

	public void setContractPayTotalUnreceived9(Double contractPayTotalUnreceived9) {
		this.contractPayTotalUnreceived9 = contractPayTotalUnreceived9;
	}

	public Double getContractPayTotal10() {
		return contractPayTotal10;
	}

	public void setContractPayTotal10(Double contractPayTotal10) {
		this.contractPayTotal10 = contractPayTotal10;
	}

	public Double getContractPayTotalReceived10() {
		return contractPayTotalReceived10;
	}

	public void setContractPayTotalReceived10(Double contractPayTotalReceived10) {
		this.contractPayTotalReceived10 = contractPayTotalReceived10;
	}

	public Double getContractPayTotalUnreceived10() {
		return contractPayTotalUnreceived10;
	}

	public void setContractPayTotalUnreceived10(Double contractPayTotalUnreceived10) {
		this.contractPayTotalUnreceived10 = contractPayTotalUnreceived10;
	}

	public Double getContractPayTotal11() {
		return contractPayTotal11;
	}

	public void setContractPayTotal11(Double contractPayTotal11) {
		this.contractPayTotal11 = contractPayTotal11;
	}

	public Double getContractPayTotalReceived11() {
		return contractPayTotalReceived11;
	}

	public void setContractPayTotalReceived11(Double contractPayTotalReceived11) {
		this.contractPayTotalReceived11 = contractPayTotalReceived11;
	}

	public Double getContractPayTotalUnreceived11() {
		return contractPayTotalUnreceived11;
	}

	public void setContractPayTotalUnreceived11(Double contractPayTotalUnreceived11) {
		this.contractPayTotalUnreceived11 = contractPayTotalUnreceived11;
	}

	public Double getContractPayTotal12() {
		return contractPayTotal12;
	}

	public void setContractPayTotal12(Double contractPayTotal12) {
		this.contractPayTotal12 = contractPayTotal12;
	}

	public Double getContractPayTotalReceived12() {
		return contractPayTotalReceived12;
	}

	public void setContractPayTotalReceived12(Double contractPayTotalReceived12) {
		this.contractPayTotalReceived12 = contractPayTotalReceived12;
	}

	public Double getContractPayTotalUnreceived12() {
		return contractPayTotalUnreceived12;
	}

	public void setContractPayTotalUnreceived12(Double contractPayTotalUnreceived12) {
		this.contractPayTotalUnreceived12 = contractPayTotalUnreceived12;
	}

	public String getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(String startDate1) {
		this.startDate1 = startDate1;
	}

	public String getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}

	public String getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(String startDate2) {
		this.startDate2 = startDate2;
	}

	public String getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(String endDate2) {
		this.endDate2 = endDate2;
	}

	public String getStartDate3() {
		return startDate3;
	}

	public void setStartDate3(String startDate3) {
		this.startDate3 = startDate3;
	}

	public String getEndDate3() {
		return endDate3;
	}

	public void setEndDate3(String endDate3) {
		this.endDate3 = endDate3;
	}

	public String getStartDate4() {
		return startDate4;
	}

	public void setStartDate4(String startDate4) {
		this.startDate4 = startDate4;
	}

	public String getEndDate4() {
		return endDate4;
	}

	public void setEndDate4(String endDate4) {
		this.endDate4 = endDate4;
	}

	public String getStartDate5() {
		return startDate5;
	}

	public void setStartDate5(String startDate5) {
		this.startDate5 = startDate5;
	}

	public String getEndDate5() {
		return endDate5;
	}

	public void setEndDate5(String endDate5) {
		this.endDate5 = endDate5;
	}

	public String getStartDate6() {
		return startDate6;
	}

	public void setStartDate6(String startDate6) {
		this.startDate6 = startDate6;
	}

	public String getEndDate6() {
		return endDate6;
	}

	public void setEndDate6(String endDate6) {
		this.endDate6 = endDate6;
	}

	public String getStartDate7() {
		return startDate7;
	}

	public void setStartDate7(String startDate7) {
		this.startDate7 = startDate7;
	}

	public String getEndDate7() {
		return endDate7;
	}

	public void setEndDate7(String endDate7) {
		this.endDate7 = endDate7;
	}

	public String getStartDate8() {
		return startDate8;
	}

	public void setStartDate8(String startDate8) {
		this.startDate8 = startDate8;
	}

	public String getEndDate8() {
		return endDate8;
	}

	public void setEndDate8(String endDate8) {
		this.endDate8 = endDate8;
	}

	public String getStartDate9() {
		return startDate9;
	}

	public void setStartDate9(String startDate9) {
		this.startDate9 = startDate9;
	}

	public String getEndDate9() {
		return endDate9;
	}

	public void setEndDate9(String endDate9) {
		this.endDate9 = endDate9;
	}

	public String getStartDate10() {
		return startDate10;
	}

	public void setStartDate10(String startDate10) {
		this.startDate10 = startDate10;
	}

	public String getEndDate10() {
		return endDate10;
	}

	public void setEndDate10(String endDate10) {
		this.endDate10 = endDate10;
	}

	public String getStartDate11() {
		return startDate11;
	}

	public void setStartDate11(String startDate11) {
		this.startDate11 = startDate11;
	}

	public String getEndDate11() {
		return endDate11;
	}

	public void setEndDate11(String endDate11) {
		this.endDate11 = endDate11;
	}

	public String getStartDate12() {
		return startDate12;
	}

	public void setStartDate12(String startDate12) {
		this.startDate12 = startDate12;
	}

	public String getEndDate12() {
		return endDate12;
	}

	public void setEndDate12(String endDate12) {
		this.endDate12 = endDate12;
	}

	public Double getContractPayTotalPart() {
		return contractPayTotalPart;
	}

	public void setContractPayTotalPart(Double contractPayTotalPart) {
		this.contractPayTotalPart = contractPayTotalPart;
	}

	public Double getContractPayTotalReceivedPart() {
		return contractPayTotalReceivedPart;
	}

	public void setContractPayTotalReceivedPart(Double contractPayTotalReceivedPart) {
		this.contractPayTotalReceivedPart = contractPayTotalReceivedPart;
	}

	public Double getContractPayTotalUnreceivedPart() {
		return contractPayTotalUnreceivedPart;
	}

	public void setContractPayTotalUnreceivedPart(Double contractPayTotalUnreceivedPart) {
		this.contractPayTotalUnreceivedPart = contractPayTotalUnreceivedPart;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}
