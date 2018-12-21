package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-25 16:32:26
 */
public class ActivityGeneralDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int payed;
	private int total;
	//
	private Long id;
	//活动名称
	private String activityName;
	//活动开始日期
	private Date startDate;
	//活动结束日期
	private Date endDate;
	//批次ID
	private String orderId;

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
	 * 设置：活动开始日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：活动开始日期
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：活动结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：活动结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：批次ID
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：批次ID
	 */
	public String getOrderId() {
		return orderId;
	}

	public int getPayed() {
		return payed;
	}

	public void setPayed(int payed) {
		this.payed = payed;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
