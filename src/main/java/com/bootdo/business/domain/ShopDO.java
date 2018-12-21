package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-22 23:19:57
 */
public class ShopDO extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long[] mergeIds;
	//
	private Long[] mergedIds;
	//楼宇ID
	private Long buildingId;
	//房间编码
	private String code;
	//房间编码
	private String[] codes;
	//编码位数
	private String codeNum;
	//编码方式（0顺序,1循环）
	private String codeType;
	//单元
	private Integer unit;
	//单元
	private Integer startUnit;
	//单元
	private Integer endUnit;
	//楼层
	private Integer floor;
	//楼层
	private Integer startFloor;
	//楼层
	private Integer endFloor;
	//用途
	private String roomType;
	//用途
	private String roomTypeName;
	//建筑面积
	private Double buildingArea;
	//使用面积
	private Double floorArea;
	//建筑面积A
	private Double buildingAreaA;
	//使用面积A
	private Double floorAreaA;
	//建筑面积B
	private Double buildingAreaB;
	//使用面积BA
	private Double floorAreaB;

	private List<ShopDO> shopDOList;

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
	 * 设置：楼宇ID
	 */
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * 获取：楼宇ID
	 */
	public Long getBuildingId() {
		return buildingId;
	}
	/**
	 * 设置：房间编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：房间编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：单元
	 */
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	/**
	 * 获取：单元
	 */
	public Integer getUnit() {
		return unit;
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
	 * 设置：用途
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	/**
	 * 获取：用途
	 */
	public String getRoomType() {
		return roomType;
	}
	/**
	 * 设置：建筑面积
	 */
	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}
	/**
	 * 获取：建筑面积
	 */
	public Double getBuildingArea() {
		return buildingArea;
	}
	/**
	 * 设置：使用面积
	 */
	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}
	/**
	 * 获取：使用面积
	 */
	public Double getFloorArea() {
		return floorArea;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public Integer getStartUnit() {
		return startUnit;
	}

	public void setStartUnit(Integer startUnit) {
		this.startUnit = startUnit;
	}

	public Integer getEndUnit() {
		return endUnit;
	}

	public void setEndUnit(Integer endUnit) {
		this.endUnit = endUnit;
	}

	public Integer getStartFloor() {
		return startFloor;
	}

	public void setStartFloor(Integer startFloor) {
		this.startFloor = startFloor;
	}

	public Integer getEndFloor() {
		return endFloor;
	}

	public void setEndFloor(Integer endFloor) {
		this.endFloor = endFloor;
	}

	public Long[] getMergeIds() {
		return mergeIds;
	}

	public void setMergeIds(Long[] mergeIds) {
		this.mergeIds = mergeIds;
	}

	public List<ShopDO> getShopDOList() {
		return shopDOList;
	}

	public void setShopDOList(List<ShopDO> shopDOList) {
		this.shopDOList = shopDOList;
	}

	public Long[] getMergedIds() {
		return mergedIds;
	}

	public void setMergedIds(Long[] mergedIds) {
		this.mergedIds = mergedIds;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}

	public Double getBuildingAreaA() {
		return buildingAreaA;
	}

	public void setBuildingAreaA(Double buildingAreaA) {
		this.buildingAreaA = buildingAreaA;
	}

	public Double getFloorAreaA() {
		return floorAreaA;
	}

	public void setFloorAreaA(Double floorAreaA) {
		this.floorAreaA = floorAreaA;
	}

	public Double getBuildingAreaB() {
		return buildingAreaB;
	}

	public void setBuildingAreaB(Double buildingAreaB) {
		this.buildingAreaB = buildingAreaB;
	}

	public Double getFloorAreaB() {
		return floorAreaB;
	}

	public void setFloorAreaB(Double floorAreaB) {
		this.floorAreaB = floorAreaB;
	}
}
