package com.snowy_samuume.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author snowy
 * @date 2020/9/19 20:45
 */
@Data
public abstract class BaseEntity implements Serializable {
    @ApiModelProperty(value = "状态 更具表选择")
    private Integer status;

    @ApiModelProperty(value = "创建人")
    private Integer createMan;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private Integer updateMan;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
