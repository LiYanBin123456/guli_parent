package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Client;
import com.atguigu.eduservice.entity.Contact;
import com.atguigu.eduservice.entity.ViewClient;
import com.atguigu.eduservice.entity.query.ClientQuery;
import com.atguigu.eduservice.service.ClientService;
import com.atguigu.eduservice.service.ViewClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.List;

/**
 * <p>
 *  客户管理
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-11
 */
@Api(description = "客户管理")
@RestController
@RequestMapping("/eduservice/client")
public class ClientController {
    //自动注入
    @Autowired
    private ClientService clientService;

    @Autowired
    private ViewClientService viewClientService;

    @ApiOperation(value ="条件查询客户列表")
    @PostMapping("getClientList/{current}/{limit}")
    public R getClientList(@PathVariable long current, @PathVariable long limit,
                           @RequestBody(required = false) ClientQuery clientQuery){
        //创建分页查询对象
        Page<ViewClient> pageClient =new Page<>(current,limit);

        //构造条件
        QueryWrapper<ViewClient> wrapper =new QueryWrapper<>();
        //多条件组合查询
        String name = clientQuery.getName();
        String pname = clientQuery.getPname();
        String contact = clientQuery.getContact();
        String control = clientQuery.getControl();

        if(!StringUtils.isEmpty(name)) {
            //构建条件，模糊查询
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(pname)) {
            //构建条件，模糊查询
            wrapper.like("pname",pname);
        }
        if(!StringUtils.isEmpty(contact)) {
            //构建条件，模糊查询
            wrapper.like("contact",contact);
        }
        if(!StringUtils.isEmpty(control)) {
            //构建条件，模糊查询
            wrapper.like("control",control);
        }

        //调用方法实现条件查询
        viewClientService.page(pageClient,wrapper);
        long total = pageClient.getTotal();//总记录数
        List<ViewClient> records = pageClient.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "按产业园查询客户列表")
    @PostMapping("findByPark/{pid}")
    public R findByPark(@PathVariable long pid) {
        //调用service的方法实现查询所有的操作
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(pid)) {
            //构建条件，模糊查询
            queryWrapper.eq("pid",pid);
        }
        List<Client> list = clientService.list(queryWrapper);
        return R.ok().data("rows",list);
    }


    @ApiOperation(value = "按渠道商查询客户列表")
    @PostMapping("findByDistribute/{did}")
    public R findByDistribute(@PathVariable long did) {
        //调用service的方法实现查询所有的操作
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(did)) {
            //构建条件，模糊查询
            queryWrapper.eq("did",did);
        }
        List<Client> list = clientService.list(queryWrapper);
        return R.ok().data("rows",list);
    }

    //查询单个客户按照id
    @GetMapping("/getClient/{id}")
    public R getClient(@PathVariable long id){
        Client client = clientService.getById(id);
        return R.ok().data("client",client);
    }

    //修改客户信息
    @PostMapping("updateClient")
    public R updateClient(@RequestBody Client client){
         boolean flag = clientService.updateById(client);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Client client) {
        boolean save = clientService.save(client);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

