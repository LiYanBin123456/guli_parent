package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Contract;
import com.atguigu.eduservice.entity.Detail1;
import com.atguigu.eduservice.service.Detail1Service;
import com.atguigu.eduservice.service.Settlement1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-19
 */
@Api(description = "客户结算单明细")
@RestController
@RequestMapping("/eduservice/detail1")
public class Detail1Controller {

    @Autowired
    private Settlement1Service settlement1Service;

    @Autowired
    private Detail1Service detail1Service;

    @ApiOperation(value = "按结算单id查询结算单明细")
    @PostMapping("getDetailBySid/{sid}")
    public R getDetails(@PathVariable long sid) {
        //调用service的方法实现查询所有的操作
        QueryWrapper<Detail1> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(sid)) {
            //相等
            queryWrapper.eq("sid",sid);
        }
        List<Detail1> list = detail1Service.list(queryWrapper);
        return R.ok().data("rows",list);
    }

}

