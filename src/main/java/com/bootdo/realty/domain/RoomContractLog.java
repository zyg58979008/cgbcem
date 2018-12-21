package com.bootdo.realty.domain;

import com.bootdo.common.domain.BaseDO;

import java.util.Date;

/**
 * Created by wzm on 2018/10/5.
 */
public class RoomContractLog extends BaseDO{
    private Long id;
    private Long contractId;
    private String name;
    private String orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
