package com.bootdo.stock.domain;

import com.bootdo.common.domain.CommonDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 08:36:23
 */
public class OutboundDO extends CommonDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//库房ID
	private String storeroomId;
	//备注
	private String remarks;
	//经手人
	private String outboundBy;


	public String getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
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
	 * 设置：经手人
	 */
	public void setOutboundBy(String outboundBy) {
		this.outboundBy = outboundBy;
	}
	/**
	 * 获取：经手人
	 */
	public String getOutboundBy() {
		return outboundBy;
	}
}
