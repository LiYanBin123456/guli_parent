package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.*;
import com.atguigu.eduservice.entity.query.ClientQuery;
import com.atguigu.eduservice.entity.query.Settlement1Query;
import com.atguigu.eduservice.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private Detail1Service detail1Service;

    @Autowired
    private ParkService parkService;

    @Autowired
    private ContractService contractService;

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


    @PostMapping("calculateSettlement")
    public R calculate(@RequestBody ViewSettlement1 settlement){
        /**
         * 结算流程
         * 1、获取产业园规则
         * 2、获取客户合同的规则
         * 3、根据结算单输入的数据计算
         * 4、插入或者修改结算单明细中
         */
        //获取产业园的规则
        Park park = parkService.getById(settlement.getPid());
        if(park==null){
           return R.error().message("请完善产业园区规则");
        }

        //获取客户合同规则
        QueryWrapper<Contract> queryContract = new QueryWrapper<>();
        queryContract.eq("bid",settlement.getCid());
        queryContract.eq("type",1);
        Contract contract = contractService.getOne(queryContract);
        if(contract==null){
            return R.error().message("请完善客户合同");
        }

        //计算政府扶持资金
        Detail1 detail1 = new Detail1();
        detail1.calculateGoverment(settlement,park);
        detail1.setType((byte) 1);
        detail1.setSid(settlement.getId());

        //计算客户扶持资金
        Detail1 detail2 = new Detail1();
        detail2.calculateClient(settlement,contract);
        detail2.setType((byte) 2);
        detail2.setSid(settlement.getId());

        //计算园区利润
        Detail1 detail3 = new Detail1();
        detail3.calculateProfit(detail1,detail2);
        detail3.setType((byte) 3);
        detail3.setSid(settlement.getId());

        List<Detail1> details = new ArrayList<>();
        details.add(detail1);
        details.add(detail2);
        details.add(detail3);
        boolean flag = detail1Service.saveOrUpdateBatch(details);

        if(flag){
           return R.ok();
        }else {
            return R.error();
        }
    }
}

