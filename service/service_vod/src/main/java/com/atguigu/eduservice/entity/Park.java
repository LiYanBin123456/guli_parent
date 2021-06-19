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
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Park对象", description="")
public class Park implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产业园名称")
    private String name;

    @ApiModelProperty(value = "增值税比例")
    private Float per1;

    @ApiModelProperty(value = "企业所得税")
    private Float per2;

    @ApiModelProperty(value = "个人所得税")
    private Float per3;

    @ApiModelProperty(value = "印花税比例")
    private Float per4;

    @ApiModelProperty(value = "城建税比例")
    private Float per5;


}
