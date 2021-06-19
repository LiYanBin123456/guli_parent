package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Contract对象", description="合同")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "合同编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "渠道商id 平台默认为0")
    private Long aid;

    @ApiModelProperty(value = "客户的id")
    private Long bid;

    @ApiModelProperty(value = "类型 1_平台和客户的合同   2_平台和渠道商的合同")
    private byte type;

    @ApiModelProperty(value = "增值税比例")
    private Float per1;

    @ApiModelProperty(value = "企业所得税比例")
    private Float per2;

    @ApiModelProperty(value = "个人所得税比例")
    private Float per3;

    @ApiModelProperty(value = "印花税比例")
    private Float per4;

    @ApiModelProperty(value = "城建税比例")
    private Float per5;

    @ApiModelProperty(value = "合同文件")
    private String img;


}
