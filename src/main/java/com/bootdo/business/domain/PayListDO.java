package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 商铺合同缴费列表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-23 22:43:04
 */
public class PayListDO extends BaseDO implements Serializable {


	private static final long serialVersionUID = 1L;
	private Double fenleiZujinReceived;
	private Double fenleiWuyeReceived;
	private Double fenleiGuanliReceived;
	private Double fenleiZujinUnreceived;
	private Double fenleiWuyeUnreceived;
	private Double fenleiGuanliUnreceived;
	private String request;
	//计费起日期
	private Date countStartDate;
	//计费止日期
	private Date countEndDate;
	private Double receivedLvyueIn;
	private Double receivedZhiliangIn;
	private Double receivedFuwuIn;
	private Double receivedZhuangxiuIn;


	private Double receivedLvyue;
	private Double receivedZhiliang;
	private Double receivedFuwu;
	private Double receivedZhuangxiu;
	//
	private Long id;
	//机构ID
	private Long deptId;
	//
	private String deptName;
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
	//(1租金，2物业费，3履约保证金，4质量保证金，5服务保证金，6装修押金，7管理费)
	private String type;
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Double getReceivedLvyue() {
		return receivedLvyue;
	}

	public void setReceivedLvyue(Double receivedLvyue) {
		this.receivedLvyue = receivedLvyue;
	}

	public Double getReceivedZhiliang() {
		return receivedZhiliang;
	}

	public void setReceivedZhiliang(Double receivedZhiliang) {
		this.receivedZhiliang = receivedZhiliang;
	}

	public Double getReceivedFuwu() {
		return receivedFuwu;
	}

	public void setReceivedFuwu(Double receivedFuwu) {
		this.receivedFuwu = receivedFuwu;
	}

	public Double getReceivedZhuangxiu() {
		return receivedZhuangxiu;
	}

	public void setReceivedZhuangxiu(Double receivedZhuangxiu) {
		this.receivedZhuangxiu = receivedZhuangxiu;
	}

	public Double getFenleiZujinUnreceived() {
		return fenleiZujinUnreceived;
	}

	public void setFenleiZujinUnreceived(Double fenleiZujinUnreceived) {
		this.fenleiZujinUnreceived = fenleiZujinUnreceived;
	}

	public Double getFenleiWuyeUnreceived() {
		return fenleiWuyeUnreceived;
	}

	public void setFenleiWuyeUnreceived(Double fenleiWuyeUnreceived) {
		this.fenleiWuyeUnreceived = fenleiWuyeUnreceived;
	}

	public Double getFenleiGuanliUnreceived() {
		return fenleiGuanliUnreceived;
	}

	public void setFenleiGuanliUnreceived(Double fenleiGuanliUnreceived) {
		this.fenleiGuanliUnreceived = fenleiGuanliUnreceived;
	}

	public Date getCountStartDate() {
		return countStartDate;
	}

	public void setCountStartDate(Date countStartDate) {
		this.countStartDate = countStartDate;
	}

	public Date getCountEndDate() {
		return countEndDate;
	}

	public void setCountEndDate(Date countEndDate) {
		this.countEndDate = countEndDate;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Double getFenleiZujinReceived() {
		return fenleiZujinReceived;
	}

	public void setFenleiZujinReceived(Double fenleiZujinReceived) {
		this.fenleiZujinReceived = fenleiZujinReceived;
	}

	public Double getFenleiWuyeReceived() {
		return fenleiWuyeReceived;
	}

	public void setFenleiWuyeReceived(Double fenleiWuyeReceived) {
		this.fenleiWuyeReceived = fenleiWuyeReceived;
	}

	public Double getFenleiGuanliReceived() {
		return fenleiGuanliReceived;
	}

	public void setFenleiGuanliReceived(Double fenleiGuanliReceived) {
		this.fenleiGuanliReceived = fenleiGuanliReceived;
	}

	public Double getReceivedLvyueIn() {
		return receivedLvyueIn;
	}

	public void setReceivedLvyueIn(Double receivedLvyueIn) {
		this.receivedLvyueIn = receivedLvyueIn;
	}

	public Double getReceivedZhiliangIn() {
		return receivedZhiliangIn;
	}

	public void setReceivedZhiliangIn(Double receivedZhiliangIn) {
		this.receivedZhiliangIn = receivedZhiliangIn;
	}

	public Double getReceivedFuwuIn() {
		return receivedFuwuIn;
	}

	public void setReceivedFuwuIn(Double receivedFuwuIn) {
		this.receivedFuwuIn = receivedFuwuIn;
	}

	public Double getReceivedZhuangxiuIn() {
		return receivedZhuangxiuIn;
	}

	public void setReceivedZhuangxiuIn(Double receivedZhuangxiuIn) {
		this.receivedZhuangxiuIn = receivedZhuangxiuIn;
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
	 * 设置：机构ID
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：机构ID
	 */
	public Long getDeptId() {
		return deptId;
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
	 * 设置：(1租金，2物业费，3履约保证金，4质量保证金，5服务保证金，6装修押金，7管理费)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：(1租金，2物业费，3履约保证金，4质量保证金，5服务保证金，6装修押金，7管理费)
	 */
	public String getType() {
		return type;
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
}
