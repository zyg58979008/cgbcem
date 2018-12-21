package com.bootdo.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-30 14:49:38
 */
public class ClassDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//分类名称
	private String name;
	//编码
	private Long code;
	//分类类型（设备、耗材等字典取值）
	private String type;
	//排序（升序）
	private BigDecimal sort;
	//父级编号
	private String parentId;
	//创建者
	private Integer createBy;
	//创建时间
	private Date createDate;
	//更新者
	private Long updateBy;
	//更新时间
	private Date updateDate;
	//删除标记
	private String delFlag;

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：编码
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	/**
	 * 获取：编码
	 */
	public Long getCode() {
		return code;
	}
	/**
	 * 设置：分类类型（设备、耗材等字典取值）
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：分类类型（设备、耗材等字典取值）
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：排序（升序）
	 */
	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序（升序）
	 */
	public BigDecimal getSort() {
		return sort;
	}
	/**
	 * 设置：父级编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级编号
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateBy() {
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
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新者
	 */
	public Long getUpdateBy() {
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
