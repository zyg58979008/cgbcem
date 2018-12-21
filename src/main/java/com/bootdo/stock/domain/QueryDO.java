package com.bootdo.stock.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-07 21:22:37
 */
public class QueryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	private String parentId;
	//商品分类
	private String category;
	private String brand;
	//商品名称
	private String name;
	//商品型号
	private String model;
	//商品单位
	private String unit;
	//商品库存量
	private Integer num;
	//库房id
	private String storeroomId;
	private String code;

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
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
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
	 * 设置：商品单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：商品单位
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：商品库存量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：商品库存量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：库房id
	 */
	public void setStoreroomId(String storeroomId) {
		this.storeroomId = storeroomId;
	}
	/**
	 * 获取：库房id
	 */
	public String getStoreroomId() {
		return storeroomId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
