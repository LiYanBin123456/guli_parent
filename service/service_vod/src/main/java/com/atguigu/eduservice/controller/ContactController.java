package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Contact;
import com.atguigu.eduservice.service.ContactService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2021-06-11
 */
@Api(description = "联系人")
@RestController
@RequestMapping("/eduservice/contact")
public class ContactController {
    //自动注入
    @Autowired
    private ContactService contactService;

    @ApiOperation(value = "按条件查询所有联系人")
    @PostMapping("findAll/{cid}/{type}")
    public R findAllContact(@PathVariable long cid,@PathVariable byte type) {
        //调用service的方法实现查询所有的操作
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(cid)) {
            //构建条件，模糊查询
            queryWrapper.eq("cid",cid);
        }
        if(!StringUtils.isEmpty(type)) {
            //相等
            queryWrapper.eq("type",type);
        }
        List<Contact> list = contactService.list(queryWrapper);

        return R.ok().data("rows",list);
    }

    @ApiOperation(value = "修改联系人")
    @PostMapping("updateContact")
    public R updateContact(@RequestBody Contact contact){
        boolean flag = contactService.updateById(contact);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加联系人")
    @PostMapping("addContact")
    public R addContact(@RequestBody Contact contact) {
        boolean save = contactService.save(contact);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

