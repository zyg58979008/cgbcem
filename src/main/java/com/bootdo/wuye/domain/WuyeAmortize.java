package com.bootdo.wuye.domain;

/**
 * Created by wzm on 2018/10/22.
 */
public class WuyeAmortize {

    private Long id;

    private Long buildingId;

    private String month;

    private Long roomId;

    private Long deptId;

    private Long wuyefeiId;

    private Double ying;

    private Double unpay;

    private Double payed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Double getYing() {
        return ying;
    }

    public void setYing(Double ying) {
        this.ying = ying;
    }

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getWuyefeiId() {
        return wuyefeiId;
    }

    public void setWuyefeiId(Long wuyefeiId) {
        this.wuyefeiId = wuyefeiId;
    }
}
