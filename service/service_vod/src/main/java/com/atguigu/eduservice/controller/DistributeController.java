package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Client;
import com.atguigu.eduservice.entity.Distribute;
import com.atguigu.eduservice.entity.query.ClientQuery;
import com.atguigu.eduservice.entity.query.DistributeQuery;
import com.atguigu.eduservice.service.DistributeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-16
 */
@Api(description = "渠道商管理")
@RestController
@RequestMapping("/eduservice/distribute")
public class DistributeController {

    //自动注入
    @Autowired
    private DistributeService distributeService;

    @ApiOperation(value ="条件查询渠道商列表")
    @PostMapping("getDistributeList/{current}/{limit}")
    public R getDistributeList(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) DistributeQuery Query){

        //创建分页查询对象
        Page<Distribute> page =new Page<>(current,limit);

        //构造条件
        QueryWrapper<Distribute> wrapper =new QueryWrapper<>();
        //多条件组合查询
        String name = Query.getName();
        String contact = Query.getContact();

        if(!StringUtils.isEmpty(name)) {
            //构建条件，模糊查询
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(contact)) {
            //构建条件，模糊查询
            wrapper.like("contact",contact);
        }
        //调用方法实现条件查询
        distributeService.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<Distribute> records = page.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //查询单个客户按照id
    @GetMapping("/getDistribute/{id}")
    public R getDistribute(@PathVariable long id){
        Distribute distribute  = distributeService.getById(id);
        return R.ok().data("distribute",distribute);
    }

    //修改客户信息
    @PostMapping("updateDistribute")
    public R updateDistribute(@RequestBody Distribute distribute){
        boolean flag = distributeService.updateById(distribute);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //添加讲师接口的方法
    @PostMapping("addDistribute")
    public R addDistribute(@RequestBody Distribute distribute) {
        boolean save = distributeService.save(distribute);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

