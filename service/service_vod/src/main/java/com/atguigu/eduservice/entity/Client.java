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
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Client对象", description="客户")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公司编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "渠道商id")
    @TableId(value = "did",type = IdType.NONE)
    private Long did;

    @ApiModelProperty(value = "产业园id")
    @TableId(value = "pid",type = IdType.NONE)
    private Long pid;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "统一社会信用代码")
    private String code;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "实际控制人")
    private String control;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String wx;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "税务基本信息 1_一般纳税人  2_小规模纳税人")
    @TableField("taxType")
    private byte taxType;

    @ApiModelProperty(value = "开户银行")
    private String bank;

    @ApiModelProperty(value = "银行账号")
    @TableField("cardNo")
    private String cardNo;

    @ApiModelProperty(value = "人力资源许可证")
    private String img1;

    @ApiModelProperty(value = "劳务派遣证")
    private String img2;

    @ApiModelProperty(value = "公司章程")
    private String img3;


}
