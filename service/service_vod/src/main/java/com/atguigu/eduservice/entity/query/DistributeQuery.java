package com.atguigu.eduservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DistributeQuery {

    @ApiModelProperty(value = "公司/个人名称")
    private String name;

    @ApiModelProperty(value = "联系人")
    private String contact;

}
