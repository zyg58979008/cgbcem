package com.bootdo.common.domain;

import com.bootdo.common.utils.IdGen;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

import java.util.Date;
import java.util.UUID;

/**
 * Created by wzm on 2018/4/4.
 */
public abstract class CommonDO<T>  {
    //备注
    private Long deptId;
    //创建者
    private String remarks;
    //创建者
    private Long createBy;
    //创建者名字
    private String createByName;
    //创建时间
    private Date createDate;
    //更新者
    private Long updateBy;
    //更新者名字
    private String updateByName;
    //更新时间
    private Date updateDate;
    //删除标记
    private String delFlag;

    public void preInsert(){
        UserDO userDO= ShiroUtils.getUser();
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
    public void preRemove(){
        UserDO userDO= ShiroUtils.getUser();
        if(userDO!=null){
            this.updateBy=userDO.getUserId();
            this.updateDate=new Date();
            this.delFlag="1";
        }
    }
    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 设置：创建者
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    /**
     * 获取：创建者
     */
    public Long getCreateBy() {
        return createBy;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * 设置：更新者
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    /**
     * 获取：更新者
     */
    public Long getUpdateBy() {
        return updateBy;
    }
    /**
     * 设置：更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * 获取：更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    /**
     * 设置：删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * 获取：删除标记
     */
    public String getDelFlag() {
        return delFlag;
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
