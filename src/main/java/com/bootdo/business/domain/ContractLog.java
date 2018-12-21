package com.bootdo.business.domain;

import com.bootdo.common.domain.BaseDO;

/**
 * Created by wzm on 2018/10/5.
 */
public class ContractLog extends BaseDO{
    private Long id;
    private String contractId;
    private String name;
    private String orderId;
    //唯一ID
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
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
