package com.bootdo.wuye.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-08 08:29:06
 */
public class QunuanfeiDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;

	private Long buildingId;
	//
	private Long[] ids;
	//购房者姓名
	private String name;
	//状态
	private String state;
	//年度
	private String year;
	//开始年度
	private String startYear;
	//结束年度
	private String endYear;
	//
	private Date startDate;
	//
	private Date endDate;
	//未交清
	private Integer unpay;
	//已交清
	private Integer payed;
	//已交清
	private Integer payedNum;
	//已交清
	private Integer unpayNum;
	//已交清
	private Integer totalNum;

	private Double month;//总月份

	private Double newMonth;//月份差

	private int num;//update的数量

	private Double qunuanYing;//月份差

	private Double qunuanUnpay;//月份差

	private Double qunuanPayed;//月份差

	private Double tingnuanYing;//月份差

	private Double tingnuanUnpay;//月份差

	private Double tingnuanPayed;//月份差

	private List<QunuanfeiDetailDO> qunuanfeiDetailDOList;
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
	/**
	 * 设置：开始年度
	 */
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	/**
	 * 获取：开始年度
	 */
	public String getStartYear() {
		return startYear;
	}
	/**
	 * 设置：结束年度
	 */
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	/**
	 * 获取：结束年度
	 */
	public String getEndYear() {
		return endYear;
	}
	/**
	 * 设置：
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：未交清
	 */
	public void setUnpay(Integer unpay) {
		this.unpay = unpay;
	}
	/**
	 * 获取：未交清
	 */
	public Integer getUnpay() {
		return unpay;
	}
	/**
	 * 设置：已交清
	 */
	public void setPayed(Integer payed) {
		this.payed = payed;
	}
	/**
	 * 获取：已交清
	 */
	public Integer getPayed() {
		return payed;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Double getMonth() {
		return month;
	}

	public void setMonth(Double month) {
		this.month = month;
	}

	public Double getNewMonth() {
		return newMonth;
	}

	public void setNewMonth(Double newMonth) {
		this.newMonth = newMonth;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<QunuanfeiDetailDO> getQunuanfeiDetailDOList() {
		return qunuanfeiDetailDOList;
	}

	public void setQunuanfeiDetailDOList(List<QunuanfeiDetailDO> qunuanfeiDetailDOList) {
		this.qunuanfeiDetailDOList = qunuanfeiDetailDOList;
	}

	public Double getQunuanYing() {
		return qunuanYing;
	}

	public void setQunuanYing(Double qunuanYing) {
		this.qunuanYing = qunuanYing;
	}

	public Double getQunuanUnpay() {
		return qunuanUnpay;
	}

	public void setQunuanUnpay(Double qunuanUnpay) {
		this.qunuanUnpay = qunuanUnpay;
	}

	public Double getQunuanPayed() {
		return qunuanPayed;
	}

	public void setQunuanPayed(Double qunuanPayed) {
		this.qunuanPayed = qunuanPayed;
	}

	public Double getTingnuanYing() {
		return tingnuanYing;
	}

	public void setTingnuanYing(Double tingnuanYing) {
		this.tingnuanYing = tingnuanYing;
	}

	public Double getTingnuanUnpay() {
		return tingnuanUnpay;
	}

	public void setTingnuanUnpay(Double tingnuanUnpay) {
		this.tingnuanUnpay = tingnuanUnpay;
	}

	public Double getTingnuanPayed() {
		return tingnuanPayed;
	}

	public void setTingnuanPayed(Double tingnuanPayed) {
		this.tingnuanPayed = tingnuanPayed;
	}

	public Integer getPayedNum() {
		return payedNum;
	}

	public void setPayedNum(Integer payedNum) {
		this.payedNum = payedNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getUnpayNum() {
		return unpayNum;
	}

	public void setUnpayNum(Integer unpayNum) {
		this.unpayNum = unpayNum;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
}
