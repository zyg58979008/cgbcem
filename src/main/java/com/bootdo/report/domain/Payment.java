package com.bootdo.report.domain;

/**
 * Created by wzm on 2018/11/21.
 */
public class Payment {
    private String date;

    private Double xianjin;

    private Double pos;

    private Double zhuanzhang;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
