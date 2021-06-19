package com.atguigu.eduservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Settlement1Query {

    @ApiModelProperty(value = "公司名称")
    private String cname;

    @ApiModelProperty(value = "产业园名称")
    private String pname;

    @ApiModelProperty(value = "客户名称")
    private String dname;

    @ApiModelProperty(value = "月份")
    private Date month;

}
