package com.atguigu.eduservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientQuery {

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "产业园名称")
    private String pname;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "实际控制人")
    private String control;

}
