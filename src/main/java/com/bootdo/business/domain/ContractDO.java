package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 商铺合同管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-07 13:26:52
 */
public class ContractDO extends BaseDO implements Serializable {
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private static final long serialVersionUID = 1L;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	private String bankName;
	//合同状态 0-生效，1-待生效，2-失效
	private String state;
	//批量ID
	private String orderId;
	//业务ID
	private Long yewuId;
	//
	private Long id;
    private String type;
    //
    private String typeName;
	//唯一ID
	private String oidOld;
    //收款人
    private String receiptBy;

	private int rowNum;
	private String unitCode;

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getReceiptBy() {
        return receiptBy;
    }

    public void setReceiptBy(String receiptBy) {
        this.receiptBy = receiptBy;
    }

    public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	private Long shopId;
	//楼宇ID
	private Long buildingId;
	//楼宇name
	private String buildingName;
	//房屋类型
	private String roomType;

	private Date now;

    private Date payDate;

	//撤场操作时间
	private Date chechangOperationDate;

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	//合同编号
	private String contractCode;
	//楼层
	private Integer floor;
	//楼层
	private String floorString;
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
	//合同期应收租金
	private Double contractPayTotalReceived;
	//合同期应收租金
	private Double contractPayTotalUnreceived;
	//合同期应收租金
	private Double contractPayTotal;
	//租金单价
	private Double unitPrice;
	//收款方式
	private String payType;
	//优惠政策
	private String youhui;
	//收入分类-租金-应收
	private Double fenleiZujin;
	//收入分类-物业费-应收
	private Double fenleiWuye;
	//收入分类-管理费-应收
	private Double fenleiGuanli;
	//合同期应收租金-截止当前日期应收（撤场用）
	private Double contractPayTotalShould;
	//收入分类-租金-截止当前日期应收（撤场用）
	private Double fenleiZujinShould;
	//收入分类-物业费-截止当前日期应收（撤场用）
	private Double fenleiWuyeShould;
	//收入分类-管理费-截止当前日期应收（撤场用）
	private Double fenleiGuanliShould;
	//收入分类-租金每月额（撤场用）
	private Double fenleiZujinMonth;
	//收入分类-物业费每月额（撤场用）
	private Double fenleiWuyeMonth;
	//收入分类-管理费每月额（撤场用）
	private Double fenleiGuanliMonth;
	//收入分类-租金差额-截止当前日期应收减已收（撤场用）
	private Double fenleiZujinDis;
	//收入分类-物业费差额-截止当前日期应收减已收（撤场用）
	private Double fenleiWuyeDis;
	//收入分类-管理费差额-截止当前日期应收减已收（撤场用）
	private Double fenleiGuanliDis;
	//合同期应收租金差额-截止当前日期应收减已收（撤场用）
	private Double contractPayTotalDis;
	//收入分类-租金每日摊销（撤场用）
	private Double fenleiZujinTanxiao;
	//收入分类-物业费每日摊销（撤场用）
	private Double fenleiWuyeTanxiao;
	//收入分类-管理费每日摊销（撤场用）
	private Double fenleiGuanliTanxiao;
	//履约保证金退补（撤场用）
	private Double lvyueDis;
	//质量保证金退补（撤场用）
	private Double zhiliangDis;
	//服务保证金退补（撤场用）
	private Double fuwuDis;
	//装修押金退补（撤场用）
	private Double zhuangxiuDis;
	//收入分类-租金-已收
	private Double fenleiZujinReceived;
	//收入分类-租金-未收
	private Double fenleiZujinUnreceived;
	//收入分类-物业费-已收
	private Double fenleiWuyeReceived;
	//收入分类-物业费-未收
	private Double fenleiWuyeUnreceived;
	//收入分类-管理费-已收
	private Double fenleiGuanliReceived;
	//收入分类-管理费-未收
	private Double fenleiGuanliUnreceived;
	private Double receivedLvyueIn;
	private Double receivedZhiliangIn;
	private Double receivedFuwuIn;
	private Double receivedZhuangxiuIn;
	//合同起
	private Date contractStartDate;
	//合同止
	private Date contractEndDate;
	//合同起
	private String contractStartDateString;
	//合同止
	private String contractEndDateString;

	private String contractChechangDateString;

	private Date contractChechangDate;

	private String contractDateString;
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
	//电话
	private String phone;
	//身份证号
	private String idCard;
	//开单编码
	private String kaidanCode;
	//开单名称
	private String kaidanName;
	//收款人
	private String payee;
	//账户
	private String account;
	//开户行
	private String bank;
	//签约日期
	private Date contractDate;
	//租赁申请表
	private String zulinbiao;
	//身份证复印件
	private String copyIdCard;
	//银行卡复印件
	private String copyBankCard;
	//图纸
	private String tuzhi;
	//备注
	private String remarks;
	//优惠后租金单价
	private Double youhuiUnitPrice;
	//每天摊销钱数
	private Double amortizePrice;
	//每天摊销钱数
	private Double amortizePriceZujin;
	//每天摊销钱数
	private Double amortizePriceWuye;
	//每天摊销钱数
	private Double amortizePriceGuanli;
	//交来类型
	private String sType;
	//
	private String sTypeName;
	//折扣
	private Double discount;
	//未缴金额总数
	private Double total;

	//唯一ID
	private String oid;

	public Long getYewuId() {
		return yewuId;
	}

	public void setYewuId(Long yewuId) {
		this.yewuId = yewuId;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

	public Date getChechangOperationDate() {
		return chechangOperationDate;
	}

	public void setChechangOperationDate(Date chechangOperationDate) {
		this.chechangOperationDate = chechangOperationDate;
	}

	public String getContractChechangDateString() {
		return contractChechangDateString;
	}

	public void setContractChechangDateString(String contractChechangDateString) {
		this.contractChechangDateString = contractChechangDateString;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public Double getContractPayTotalDis() {
		return contractPayTotalDis;
	}

	public void setContractPayTotalDis(Double contractPayTotalDis) {
		this.contractPayTotalDis = contractPayTotalDis;
	}

	public Double getContractPayTotalShould() {
		return contractPayTotalShould;
	}

	public void setContractPayTotalShould(Double contractPayTotalShould) {
		this.contractPayTotalShould = contractPayTotalShould;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	 * 设置：优惠政策
	 */
	public void setYouhui(String youhui) {
		this.youhui = youhui;
	}
	/**
	 * 获取：优惠政策
	 */
	public String getYouhui() {
		return youhui;
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
	 * 设置：合同起
	 */
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	/**
	 * 获取：合同起
	 */
	public Date getContractStartDate() {
		return contractStartDate;
	}
	/**
	 * 设置：合同止
	 */
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * 获取：合同止
	 */
	public Date getContractEndDate() {
		return contractEndDate;
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
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * 设置：开单编码
	 */
	public void setKaidanCode(String kaidanCode) {
		this.kaidanCode = kaidanCode;
	}
	/**
	 * 获取：开单编码
	 */
	public String getKaidanCode() {
		return kaidanCode;
	}
	/**
	 * 设置：开单名称
	 */
	public void setKaidanName(String kaidanName) {
		this.kaidanName = kaidanName;
	}
	/**
	 * 获取：开单名称
	 */
	public String getKaidanName() {
		return kaidanName;
	}
	/**
	 * 设置：收款人
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}
	/**
	 * 获取：收款人
	 */
	public String getPayee() {
		return payee;
	}
	/**
	 * 设置：账户
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账户
	 */
	public String getAccount() {
		return account;
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
	 * 设置：签约日期
	 */
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	/**
	 * 获取：签约日期
	 */
	public Date getContractDate() {
		return contractDate;
	}
	/**
	 * 设置：租赁申请表
	 */
	public void setZulinbiao(String zulinbiao) {
		this.zulinbiao = zulinbiao;
	}
	/**
	 * 获取：租赁申请表
	 */
	public String getZulinbiao() {
		return zulinbiao;
	}
	/**
	 * 设置：身份证复印件
	 */
	public void setCopyIdCard(String copyIdCard) {
		this.copyIdCard = copyIdCard;
	}
	/**
	 * 获取：身份证复印件
	 */
	public String getCopyIdCard() {
		return copyIdCard;
	}
	/**
	 * 设置：银行卡复印件
	 */
	public void setCopyBankCard(String copyBankCard) {
		this.copyBankCard = copyBankCard;
	}
	/**
	 * 获取：银行卡复印件
	 */
	public String getCopyBankCard() {
		return copyBankCard;
	}
	/**
	 * 设置：图纸
	 */
	public void setTuzhi(String tuzhi) {
		this.tuzhi = tuzhi;
	}
	/**
	 * 获取：图纸
	 */
	public String getTuzhi() {
		return tuzhi;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Double getFenleiZujinReceived() {
		return fenleiZujinReceived;
	}

	public void setFenleiZujinReceived(Double fenleiZujinReceived) {
		this.fenleiZujinReceived = fenleiZujinReceived;
	}

	public Double getFenleiZujinUnreceived() {
		return fenleiZujinUnreceived;
	}

	public void setFenleiZujinUnreceived(Double fenleiZujinUnreceived) {
		this.fenleiZujinUnreceived = fenleiZujinUnreceived;
	}

	public Double getFenleiWuyeReceived() {
		return fenleiWuyeReceived;
	}

	public void setFenleiWuyeReceived(Double fenleiWuyeReceived) {
		this.fenleiWuyeReceived = fenleiWuyeReceived;
	}

	public Double getFenleiWuyeUnreceived() {
		return fenleiWuyeUnreceived;
	}

	public void setFenleiWuyeUnreceived(Double fenleiWuyeUnreceived) {
		this.fenleiWuyeUnreceived = fenleiWuyeUnreceived;
	}

	public Double getFenleiGuanliReceived() {
		return fenleiGuanliReceived;
	}

	public void setFenleiGuanliReceived(Double fenleiGuanliReceived) {
		this.fenleiGuanliReceived = fenleiGuanliReceived;
	}

	public Double getFenleiGuanliUnreceived() {
		return fenleiGuanliUnreceived;
	}

	public Double getContractPayTotalReceived() {
		return contractPayTotalReceived;
	}

	public void setContractPayTotalReceived(Double contractPayTotalReceived) {
		this.contractPayTotalReceived = contractPayTotalReceived;
	}

	public Double getContractPayTotalUnreceived() {
		return contractPayTotalUnreceived;
	}

	public void setContractPayTotalUnreceived(Double contractPayTotalUnreceived) {
		this.contractPayTotalUnreceived = contractPayTotalUnreceived;
	}

	public void setFenleiGuanliUnreceived(Double fenleiGuanliUnreceived) {
		this.fenleiGuanliUnreceived = fenleiGuanliUnreceived;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Double getAmortizePriceZujin() {
		return amortizePriceZujin;
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

	public String getContractDateString() {
		return contractDateString;
	}

	public void setContractDateString(String contractDateString) {
		this.contractDateString = contractDateString;
	}

	public String getOidOld() {
		return oidOld;
	}

	public void setOidOld(String oidOld) {
		this.oidOld = oidOld;
	}

	public Double getFenleiZujinShould() {
		return fenleiZujinShould;
	}

	public void setFenleiZujinShould(Double fenleiZujinShould) {
		this.fenleiZujinShould = fenleiZujinShould;
	}

	public Double getFenleiWuyeShould() {
		return fenleiWuyeShould;
	}

	public void setFenleiWuyeShould(Double fenleiWuyeShould) {
		this.fenleiWuyeShould = fenleiWuyeShould;
	}

	public Double getFenleiGuanliShould() {
		return fenleiGuanliShould;
	}

	public void setFenleiGuanliShould(Double fenleiGuanliShould) {
		this.fenleiGuanliShould = fenleiGuanliShould;
	}

	public Double getFenleiZujinDis() {
		return fenleiZujinDis;
	}

	public void setFenleiZujinDis(Double fenleiZujinDis) {
		this.fenleiZujinDis = fenleiZujinDis;
	}

	public Double getFenleiWuyeDis() {
		return fenleiWuyeDis;
	}

	public void setFenleiWuyeDis(Double fenleiWuyeDis) {
		this.fenleiWuyeDis = fenleiWuyeDis;
	}

	public Double getFenleiGuanliDis() {
		return fenleiGuanliDis;
	}

	public void setFenleiGuanliDis(Double fenleiGuanliDis) {
		this.fenleiGuanliDis = fenleiGuanliDis;
	}

	public Double getLvyueDis() {
		return lvyueDis;
	}

	public void setLvyueDis(Double lvyueDis) {
		this.lvyueDis = lvyueDis;
	}

	public Double getZhiliangDis() {
		return zhiliangDis;
	}

	public void setZhiliangDis(Double zhiliangDis) {
		this.zhiliangDis = zhiliangDis;
	}

	public Double getFuwuDis() {
		return fuwuDis;
	}

	public void setFuwuDis(Double fuwuDis) {
		this.fuwuDis = fuwuDis;
	}

	public Double getZhuangxiuDis() {
		return zhuangxiuDis;
	}

	public void setZhuangxiuDis(Double zhuangxiuDis) {
		this.zhuangxiuDis = zhuangxiuDis;
	}

	public Double getFenleiZujinMonth() {
		return fenleiZujinMonth;
	}

	public void setFenleiZujinMonth(Double fenleiZujinMonth) {
		this.fenleiZujinMonth = fenleiZujinMonth;
	}

	public Double getFenleiWuyeMonth() {
		return fenleiWuyeMonth;
	}

	public void setFenleiWuyeMonth(Double fenleiWuyeMonth) {
		this.fenleiWuyeMonth = fenleiWuyeMonth;
	}

	public Double getFenleiGuanliMonth() {
		return fenleiGuanliMonth;
	}

	public void setFenleiGuanliMonth(Double fenleiGuanliMonth) {
		this.fenleiGuanliMonth = fenleiGuanliMonth;
	}

	public Date getContractChechangDate() {
		return contractChechangDate;
	}

	public void setContractChechangDate(Date contractChechangDate) {
		this.contractChechangDate = contractChechangDate;
	}

	public Double getFenleiZujinTanxiao() {
		return fenleiZujinTanxiao;
	}

	public void setFenleiZujinTanxiao(Double fenleiZujinTanxiao) {
		this.fenleiZujinTanxiao = fenleiZujinTanxiao;
	}

	public Double getFenleiWuyeTanxiao() {
		return fenleiWuyeTanxiao;
	}

	public void setFenleiWuyeTanxiao(Double fenleiWuyeTanxiao) {
		this.fenleiWuyeTanxiao = fenleiWuyeTanxiao;
	}

	public Double getFenleiGuanliTanxiao() {
		return fenleiGuanliTanxiao;
	}

	public void setFenleiGuanliTanxiao(Double fenleiGuanliTanxiao) {
		this.fenleiGuanliTanxiao = fenleiGuanliTanxiao;
	}

	public String getFloorString() {
		return floorString;
	}

	public void setFloorString(String floorString) {
		this.floorString = floorString;
	}
}
