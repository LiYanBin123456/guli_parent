package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Distribute;
import com.atguigu.eduservice.entity.Park;
import com.atguigu.eduservice.entity.query.DistributeQuery;
import com.atguigu.eduservice.entity.query.ParkQuery;
import com.atguigu.eduservice.service.ParkService;
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
@Api(description = "产业园管理")
@RestController
@RequestMapping("/eduservice/park")
public class ParkController {

    @Autowired
    private ParkService parkService;

    @ApiOperation(value ="条件查询渠道商列表")
    @PostMapping("getParkList/{current}/{limit}")
    public R getDistributeList(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) ParkQuery Query){

        //创建分页查询对象
        Page<Park> page =new Page<>(current,limit);

        //构造条件
        QueryWrapper<Park> wrapper =new QueryWrapper<>();
        //多条件组合查询
        String name = Query.getName();

        if(!StringUtils.isEmpty(name)) {
            //构建条件，模糊查询
            wrapper.like("name",name);
        }

        //调用方法实现条件查询
        parkService.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<Park> records = page.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //查询单个客户按照id
    @GetMapping("/getPark/{id}")
    public R getPark(@PathVariable long id){
        Park park = parkService.getById(id);
        return R.ok().data("distribute",park);
    }

    //修改客户信息
    @PostMapping("updatePark")
    public R updatePark(@RequestBody Park park){
        boolean flag = parkService.updateById(park);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //添加讲师接口的方法
    @PostMapping("addPark")
    public R addPark(@RequestBody Park park) {
        boolean save = parkService.save(park);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

