package com.atguigu.eduservice.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ViewSettlement2对象", description="VIEW")
public class ViewSettlement2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long did;

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

    @ApiModelProperty(value = "公司/个人名称")
    private String dname;


}
