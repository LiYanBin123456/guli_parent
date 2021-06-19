package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Client;
import com.atguigu.eduservice.entity.Settlement1;
import com.atguigu.eduservice.entity.ViewSettlement1;
import com.atguigu.eduservice.entity.query.ClientQuery;
import com.atguigu.eduservice.entity.query.Settlement1Query;
import com.atguigu.eduservice.service.Settlement1Service;
import com.atguigu.eduservice.service.ViewSettlement1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-18
 */
@Api(description = "客户结算单管理")
@RestController
@RequestMapping("/eduservice/settlement1")
public class Settlement1Controller {

    @Autowired
    private Settlement1Service settlement1Service;

    @Autowired
    private ViewSettlement1Service viewSettlement1Service;

    @ApiOperation(value ="条件查询客户结算单视图列表")
    @PostMapping("getSettlementList/{current}/{limit}")
    public R getSettlementList(@PathVariable long current, @PathVariable long limit,
                           @RequestBody(required = false) Settlement1Query settlement1Query){
        //创建分页查询对象
        Page<ViewSettlement1> page =new Page<>(current,limit);
        //构造条件
        QueryWrapper<ViewSettlement1> wrapper =new QueryWrapper<>();

        //多条件组合查询
        String cname = settlement1Query.getCname();
        String pname = settlement1Query.getPname();
        Date month = settlement1Query.getMonth();

        if(!StringUtils.isEmpty(cname)) {
            //构建条件，模糊查询
            wrapper.like("cname",cname);
        }
        if(!StringUtils.isEmpty(pname)) {
            //构建条件，模糊查询
            wrapper.like("pname",pname);
        }
        if(!StringUtils.isEmpty(month)) {
            //构建条件，模糊查询
            wrapper.like("month",month);
        }

        //调用方法实现条件查询
        viewSettlement1Service.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<ViewSettlement1> records = page.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //修改客户信息
    @PostMapping("updateSettlement")
    public R updateSettlement(@RequestBody Settlement1 settlement){
        boolean flag = settlement1Service.updateById(settlement);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //修改客户信息
    @PostMapping("addSettlement")
    public R addSettlement(@RequestBody Settlement1 settlement){
        boolean flag = settlement1Service.save(settlement);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

