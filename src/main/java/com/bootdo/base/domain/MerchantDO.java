package com.bootdo.base.domain;

import com.bootdo.common.domain.CommonDO;

import java.io.Serializable;

/**
 *
 */
public class MerchantDO extends CommonDO<MerchantDO> implements Serializable {
    private String juridicalPerson; 		// 法人姓名
    private String brand; 	// 品牌
    private String idCard; 	// 身份证号
    private String phone; 	// 联系电话
    private String address; 	// 住址
    private String openingBank; 	// 开户行
    private String cardNo; 	// 银行卡号
    private String id; 	// id
    private String orderId;
    private String payee;//收款人
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJuridicalPerson() {
        return juridicalPerson;
    }

    public void setJuridicalPerson(String juridicalPerson) {
        this.juridicalPerson = juridicalPerson;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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


}
