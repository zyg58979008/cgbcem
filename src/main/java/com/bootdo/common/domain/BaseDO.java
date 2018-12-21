package com.bootdo.common.domain;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

import java.util.Date;

public class BaseDO {
    //机构ID
    private Long deptId;
    //机构名称
    private String deptName;
    //备注
    private String remarks;
    //创建者
    private Long createBy;
    //创建者
    private String createByName;
    //创建时间
    private Date createDate;
    //更新者
    private Long updateBy;
    //创建者
    private String updateByName;
    //更新时间
    private Date updateDate;
    //删除标记
    private String delFlag;
    public void preInsert(){
        UserDO userDO= ShiroUtils.getUser();
        this.deptId=userDO.getDeptId();
        if(userDO!=null){
            this.createBy=userDO.getUserId();
            this.updateBy=userDO.getUserId();
        }
        this.createDate=new Date();
        this.updateDate=this.createDate;
        this.delFlag="0";
    }
    public void preUpdate(){
        UserDO userDO= ShiroUtils.getUser();
        if(userDO!=null){
            this.updateBy=userDO.getUserId();
            this.updateDate=new Date();
        }
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }
}
