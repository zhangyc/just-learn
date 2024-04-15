package com.hackorange.pixify.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@TableName("tb_operation_log")
@ApiModel(value = "OperationLog对象", description = "")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("操作模块")
    private String optModule;

    @ApiModelProperty("操作类型")
    private String optType;

    @ApiModelProperty("操作url")
    private String optUrl;

    @ApiModelProperty("操作方法")
    private String optMethod;

    @ApiModelProperty("操作描述")
    private String optDesc;

    @ApiModelProperty("请求参数")
    private String requestParam;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("返回数据")
    private String responseData;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("操作ip")
    private String ipAddress;

    @ApiModelProperty("操作地址")
    private String ipSource;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptModule() {
        return optModule;
    }

    public void setOptModule(String optModule) {
        this.optModule = optModule;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptUrl() {
        return optUrl;
    }

    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }

    public String getOptMethod() {
        return optMethod;
    }

    public void setOptMethod(String optMethod) {
        this.optMethod = optMethod;
    }

    public String getOptDesc() {
        return optDesc;
    }

    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpSource() {
        return ipSource;
    }

    public void setIpSource(String ipSource) {
        this.ipSource = ipSource;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
        "id = " + id +
        ", optModule = " + optModule +
        ", optType = " + optType +
        ", optUrl = " + optUrl +
        ", optMethod = " + optMethod +
        ", optDesc = " + optDesc +
        ", requestParam = " + requestParam +
        ", requestMethod = " + requestMethod +
        ", responseData = " + responseData +
        ", userId = " + userId +
        ", nickname = " + nickname +
        ", ipAddress = " + ipAddress +
        ", ipSource = " + ipSource +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
