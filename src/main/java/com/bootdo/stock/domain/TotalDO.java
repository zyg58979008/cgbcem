package com.bootdo.stock.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-11 16:25:30
 */
public class TotalDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String type;
	//
	private String inbound;
	//
	private String outbound;
	//
	private String repair;
	//
	private String reject;
	//
	private String a;
	//
	private String a1;
	//
	private String a2;

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
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setInbound(String inbound) {
		this.inbound = inbound;
	}
	/**
	 * 获取：
	 */
	public String getInbound() {
		return inbound;
	}
	/**
	 * 设置：
	 */
	public void setOutbound(String outbound) {
		this.outbound = outbound;
	}
	/**
	 * 获取：
	 */
	public String getOutbound() {
		return outbound;
	}
	/**
	 * 设置：
	 */
	public void setRepair(String repair) {
		this.repair = repair;
	}
	/**
	 * 获取：
	 */
	public String getRepair() {
		return repair;
	}
	/**
	 * 设置：
	 */
	public void setReject(String reject) {
		this.reject = reject;
	}
	/**
	 * 获取：
	 */
	public String getReject() {
		return reject;
	}
	/**
	 * 设置：
	 */
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * 获取：
	 */
	public String getA() {
		return a;
	}
	/**
	 * 设置：
	 */
	public void setA1(String a1) {
		this.a1 = a1;
	}
	/**
	 * 获取：
	 */
	public String getA1() {
		return a1;
	}
	/**
	 * 设置：
	 */
	public void setA2(String a2) {
		this.a2 = a2;
	}
	/**
	 * 获取：
	 */
	public String getA2() {
		return a2;
	}
}
