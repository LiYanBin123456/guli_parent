package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Settlement1;
import com.atguigu.eduservice.entity.Settlement2;
import com.atguigu.eduservice.entity.ViewSettlement1;
import com.atguigu.eduservice.entity.ViewSettlement2;
import com.atguigu.eduservice.entity.query.Settlement1Query;
import com.atguigu.eduservice.service.Settlement2Service;
import com.atguigu.eduservice.service.ViewSettlement2Service;
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
@Api(description = "渠道商结算单")
@RestController
@RequestMapping("/eduservice/settlement2")
public class Settlement2Controller {

    @Autowired
    private Settlement2Service settlement2Service;

    @Autowired
    private ViewSettlement2Service viewSettlement2Service;

    @ApiOperation(value ="条件查询客户结算单视图列表")
    @PostMapping("getSettlementList/{current}/{limit}")
    public R getSettlementList(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) Settlement1Query settlement1Query){
        //创建分页查询对象
        Page<ViewSettlement2> page =new Page<>(current,limit);
        //构造条件
        QueryWrapper<ViewSettlement2> wrapper =new QueryWrapper<>();

        //多条件组合查询
        String dname = settlement1Query.getDname();
        Date month = settlement1Query.getMonth();

        if(!StringUtils.isEmpty(dname)) {
            //构建条件，模糊查询
            wrapper.like("dname",dname);
        }
        if(!StringUtils.isEmpty(month)) {
            //构建条件，模糊查询
            wrapper.like("month",month);
        }
        //调用方法实现条件查询
        viewSettlement2Service.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<ViewSettlement2> records = page.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //修改客户信息
    @PostMapping("updateSettlement")
    public R updateSettlement(@RequestBody Settlement2 settlement){
        boolean flag = settlement2Service.updateById(settlement);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //修改客户信息
    @PostMapping("addSettlement")
    public R addSettlement(@RequestBody Settlement2 settlement){
        boolean flag = settlement2Service.save(settlement);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

