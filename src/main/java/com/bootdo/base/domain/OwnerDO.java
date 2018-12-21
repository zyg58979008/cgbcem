package com.bootdo.base.domain;

import com.bootdo.common.domain.CommonDO;

import java.io.Serializable;

/**
 *
 */
public class OwnerDO extends CommonDO<OwnerDO> implements Serializable {
    private String ownerName; 		// 业主姓名
    private String idCard; 	// 身份证号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String phone; 	// 联系电话
    private String id;
    private String address; 	// 住址
    private String openingBank; 	// 开户行
    private String cardNo; 	// 银行卡号
    private String orderId;
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
