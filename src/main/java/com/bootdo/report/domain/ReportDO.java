package com.bootdo.report.domain;

import com.bootdo.common.domain.BaseDO;

/**
 * Created by wzm on 2018/11/3.
 */
public class ReportDO extends BaseDO{
    //未付金额
    private Double unpay;
    //已付金额
    private Double payed;
    private String rentArea;
    private String unRentArea;
    private String name;
    private String contractor;
    private String month;
private String lastMonth;
    private String buildingId;
    private String brand;
    private String roomCode;
private String startDate;
    private String endDate;
    private int num;
    private int offset;
    // 每页条数
    private int limit;

    public Double getUnpay() {
        return unpay;
    }

    public void setUnpay(Double unpay) {
        this.unpay = unpay;
    }

    public Double getPayed() {
        return payed;
    }

    public void setPayed(Double payed) {
        this.payed = payed;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getUnRentArea() {
        return unRentArea;
    }

    public void setUnRentArea(String unRentArea) {
        this.unRentArea = unRentArea;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(String lastMonth) {
        this.lastMonth = lastMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }
}
