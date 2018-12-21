package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xinxi on 2018-10-25.
 */
public class ProfitDO extends BaseDO implements Serializable{
    private Long id;

    private Double profit;
    private String state;
    //
    private Date startDate;
    //
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
