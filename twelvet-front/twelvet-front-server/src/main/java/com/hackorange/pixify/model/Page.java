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
 * 页面
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@TableName("tb_page")
@ApiModel(value = "Page对象", description = "页面")
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页面id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("页面名")
    private String pageName;

    @ApiModelProperty("页面标签")
    private String pageLabel;

    @ApiModelProperty("页面封面")
    private String pageCover;

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

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageLabel() {
        return pageLabel;
    }

    public void setPageLabel(String pageLabel) {
        this.pageLabel = pageLabel;
    }

    public String getPageCover() {
        return pageCover;
    }

    public void setPageCover(String pageCover) {
        this.pageCover = pageCover;
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
        return "Page{" +
        "id = " + id +
        ", pageName = " + pageName +
        ", pageLabel = " + pageLabel +
        ", pageCover = " + pageCover +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
