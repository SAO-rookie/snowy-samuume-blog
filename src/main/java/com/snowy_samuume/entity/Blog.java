package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 雪域人
 * @date 2020-09-19 20:52:57
 * @email 
 */
@Data
@ApiModel("博客表")
@TableName("t_blog")
public class Blog extends BaseEntity {
    //序列化ID
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客id")
    @TableId(type = IdType.INPUT)
    private String id;

    @NotBlank
    @ApiModelProperty(value = "标题")
    private String title;

    @NotBlank
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "封面")
    private String firstPicture;

    @ApiModelProperty(value = "浏览次数")
    private Integer views;

    @ApiModelProperty(value = "标记  0 原创 1转载 2翻译")
    private Integer mark;

    @ApiModelProperty(value = "是否打赏  0打赏 1 不打赏 ")
    private Integer appreciation;

    @ApiModelProperty(value = "是否允许转载  0 允许 1 不允许")
    private Integer shareStatement;

    @ApiModelProperty(value = "是否开启评论 0 开启 1 不开启")
    private Integer commentable;

    @ApiModelProperty(value = "是否发布 0 发布 1 不发布")
    private Integer published;

    @ApiModelProperty(value = "是否推荐  0 推荐 1 不推荐 ")
    private Integer recommened;

    @ApiModelProperty(value = "类型Id")
    private Integer typeId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

}