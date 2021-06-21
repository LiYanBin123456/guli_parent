package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ViewSettlement1对象", description="")
public class ViewSettlement1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "外键 客户id")
    private Long cid;

    @ApiModelProperty(value = "外键 产业园id")
    private Long pid;

    @ApiModelProperty(value = "客户姓名")
    private String cname;

    @ApiModelProperty(value = "产业园姓名")
    private String pname;

    @ApiModelProperty(value = "月份")
    private Date month;

    @ApiModelProperty(value = "总额")
    private BigDecimal base;

    @ApiModelProperty(value = "营业额")
    private BigDecimal turnovers;

    @ApiModelProperty(value = "增值税")
    private BigDecimal tax1;

    @ApiModelProperty(value = "企业所得税")
    private BigDecimal tax2;

    @ApiModelProperty(value = "个人所得税")
    private BigDecimal tax3;

    @ApiModelProperty(value = "附加税")
    private BigDecimal tax4;

    @ApiModelProperty(value = "附加税")
    private BigDecimal tax5;

    @ApiModelProperty(value = "附加税")
    private BigDecimal tax6;

    @ApiModelProperty(value = "附加税")
    private BigDecimal tax7;

    @ApiModelProperty(value = "附加税")
    private BigDecimal taxAmount;


}
