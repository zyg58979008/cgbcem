package com.bootdo.wuye.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-08 08:29:06
 */
public class WuyefeiDO  extends BaseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long buildingId;
	//
	private Long[] ids;
	//购房者姓名
	private String name;
	//状态
	private String state;
	//状态
	private String status;
	//年度
	private String year;
	//开始年度
	private int startYear;
	//结束年度
	private int endYear;
	//开始年度
	private int startMonth;
	//结束年度
	private int endMonth;
	//
	private Date startDate;
	//
	private Date endDate;
	//未交清
	private Integer unpay;
	//已交清
	private Integer payed;

	private Double month;//总月份

	private Double newMonth;//月份差

	private int num;//update的数量

	private Double moneyYing;//月份差

	private Double moneyUnpay;//月份差

	private Double moneyPayed;//月份差

	private Double area;//月份差

	private Long roomId;//交房生成物业费时查询用

	private String roomCode;//交房生成物业费时查询用

	private Double months;//月份差

	private Double xianjin;//月份差

	private Double pos;//月份差

	private Double zhuanzhang;//月份差

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
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	/**
	 * 获取：开始年度
	 */
	public int getStartYear() {
		return startYear;
	}
	/**
	 * 设置：结束年度
	 */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	/**
	 * 获取：结束年度
	 */
	public int getEndYear() {
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

	public Double getMoneyYing() {
		return moneyYing;
	}

	public void setMoneyYing(Double moneyYing) {
		this.moneyYing = moneyYing;
	}

	public Double getMoneyUnpay() {
		return moneyUnpay;
	}

	public void setMoneyUnpay(Double moneyUnpay) {
		this.moneyUnpay = moneyUnpay;
	}

	public Double getMoneyPayed() {
		return moneyPayed;
	}

	public void setMoneyPayed(Double moneyPayed) {
		this.moneyPayed = moneyPayed;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Double getMonths() {
		return months;
	}

	public void setMonths(Double months) {
		this.months = months;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getXianjin() {
		return xianjin;
	}

	public void setXianjin(Double xianjin) {
		this.xianjin = xianjin;
	}

	public Double getPos() {
		return pos;
	}

	public void setPos(Double pos) {
		this.pos = pos;
	}

	public Double getZhuanzhang() {
		return zhuanzhang;
	}

	public void setZhuanzhang(Double zhuanzhang) {
		this.zhuanzhang = zhuanzhang;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
}
