package com.bootdo.stock.domain;

import com.bootdo.common.domain.CommonDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 15:32:04
 */
public class RejectDO extends CommonDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//库房ID
	private String storeroomId;
	//经手人
	private String inboundBy;
	//备注
	private String remarks;

	/**
	 * 设置：库房ID
	 */
	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	/**
	 * 获取：库房ID
	 */
	public String getStoreroomId() {
		return storeroomId;
	}
	/**
	 * 设置：经手人
	 */
	public void setInboundBy(String inboundBy) {
		this.inboundBy = inboundBy;
	}
	/**
	 * 获取：经手人
	 */
	public String getInboundBy() {
		return inboundBy;
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

}
