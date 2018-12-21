package com.bootdo.stock.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-10 17:14:31
 */
public class CopyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//商品品牌
	private String brand;
	//
	private String name;
	//使用年限
	private String durableYears;
	//商品编码
	private String code;
	//商品分类
	private String category;
	//商品型号
	private String model;
	//设备状态
	private String state;
	//机构id
	private String deptId;
	//备注
	private String remarks;
	//创建者
	private String createBy;
	//创建时间
	private Date createDate;
	//更新者
	private String updateBy;
	//更新时间
	private Date updateDate;
	//删除标记
	private String delFlag;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：商品品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：商品品牌
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：使用年限
	 */
	public void setDurableYears(String durableYears) {
		this.durableYears = durableYears;
	}
	/**
	 * 获取：使用年限
	 */
	public String getDurableYears() {
		return durableYears;
	}
	/**
	 * 设置：商品编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：商品编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：商品分类
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 获取：商品分类
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 设置：商品型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获取：商品型号
	 */
	public String getModel() {
		return model;
	}
	/**
	 * 设置：设备状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：设备状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：机构id
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：机构id
	 */
	public String getDeptId() {
		return deptId;
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
	 * 设置：创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：删除标记
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：删除标记
	 */
	public String getDelFlag() {
		return delFlag;
	}
}
