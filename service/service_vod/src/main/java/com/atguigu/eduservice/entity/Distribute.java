package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Distribute对象", description="")
public class Distribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公司/个人名称")
    private String name;

    @ApiModelProperty(value = "统一社会信用代码")
    private String code;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String wx;

    @ApiModelProperty(value = "开户银行")
    private String bank;

    @ApiModelProperty(value = "银行卡号")
    @TableField("cardNo")
    private String cardNo;


}
