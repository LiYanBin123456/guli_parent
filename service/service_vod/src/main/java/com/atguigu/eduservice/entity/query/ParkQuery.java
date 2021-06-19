package com.atguigu.eduservice.entity.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParkQuery {

    @ApiModelProperty(value = "名称")
    private String name;

}
