package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.Contact;
import com.atguigu.eduservice.entity.Contract;
import com.atguigu.eduservice.service.ContractService;
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
 * @since 2021-06-15
 */
@Api(description = "合同")
@RestController
@RequestMapping("/eduservice/contract")
public class ContractController {
    //自动注入
    @Autowired
    private ContractService contractService;

    /**
     *
     * @param aid 甲方id
     * @param bid 乙方id
     * @param type 合同类型 1 平台与客户的合同  2 渠道商与客户的合同
     * @return
     */
    @ApiOperation(value = "按条件查询所有合同")
    @PostMapping("findAll/{bid}/{type}")
    public R findAllContact(@PathVariable long bid, @PathVariable byte type) {
        //调用service的方法实现查询所有的操作
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件

        if(!StringUtils.isEmpty(bid)) {
            //相等
            queryWrapper.eq("bid",bid);
        }

        if(!StringUtils.isEmpty(type)) {
            //相等
            queryWrapper.eq("type",type);
        }

        List<Contract> list = contractService.list(queryWrapper);

        return R.ok().data("rows",list);
    }

    @ApiOperation(value = "修改合同")
    @PostMapping("updateContract")
    public R updateContract(@RequestBody Contract contract){
        boolean flag = contractService.updateById(contract);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "添加合同")
    @PostMapping("addContract")
    public R addContract(@RequestBody Contract contract) {
        boolean save = contractService.save(contract);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

