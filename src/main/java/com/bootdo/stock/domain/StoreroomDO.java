package com.bootdo.stock.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-02 16:32:30
 */
public class StoreroomDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//库房名称
	private String name;
	//部门ID
	private String deptId;
	//库房地址
	private String address;
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
	 * 设置：库房名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：库房名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：部门ID
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门ID
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置：库房地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：库房地址
	 */
	public String getAddress() {
		return address;
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
