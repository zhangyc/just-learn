package com.hackorange.pixify.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@TableName("tb_user_auth")
@ApiModel(value = "UserAuth对象", description = "")
@Builder
@Data
public class UserAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户信息id")
    private Integer userInfoId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("登录类型")
    private Integer loginType;

    @ApiModelProperty("用户登录ip")
    private String ipAddress;

    @ApiModelProperty("ip来源")
    private String ipSource;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("上次登录时间")
    private LocalDateTime lastLoginTime;

    @Override
    public String toString() {
        return "UserAuth{" +
        "id = " + id +
        ", userInfoId = " + userInfoId +
        ", username = " + username +
        ", password = " + password +
        ", loginType = " + loginType +
        ", ipAddress = " + ipAddress +
        ", ipSource = " + ipSource +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", lastLoginTime = " + lastLoginTime +
        "}";
    }
}
