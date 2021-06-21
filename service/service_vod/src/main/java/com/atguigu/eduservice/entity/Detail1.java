package com.atguigu.eduservice.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Detail1对象", description="")
public class Detail1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "结算单id")
    private Long sid;

    @ApiModelProperty(value = "1_政府扶持资金   2_客户扶持资金  3_园区利润")
    private byte type;

    @ApiModelProperty(value = "营业额")
    private BigDecimal turnovers;

    @ApiModelProperty(value = "增值税")
    private BigDecimal tax1;

    @ApiModelProperty(value = "企业所得税")
    private BigDecimal tax2;

    @ApiModelProperty(value = "个人所得税")
    private BigDecimal tax3;

    @ApiModelProperty(value = "城市维护建设税")
    private BigDecimal tax4;

    @ApiModelProperty(value = "教育费附加")
    private BigDecimal tax5;

    @ApiModelProperty(value = "地方教育附加")
    private BigDecimal tax6;

    @ApiModelProperty(value = "印花税")
    private BigDecimal tax7;

    @ApiModelProperty(value = "税务合计")
    private BigDecimal taxAmount;

    /**
     * 计算政府扶持资金
     * @param settlement  结算单
     * @param park 产业员工规则
     */
    public void calculateGoverment(ViewSettlement1 settlement, Park park){
        this.setTax1(settlement.getTax1().multiply(BigDecimal.valueOf(park.getPer1()/100)));
        this.setTax2(settlement.getTax2().multiply(BigDecimal.valueOf(park.getPer2()/100)));
        this.setTax3(settlement.getTax3().multiply(BigDecimal.valueOf(park.getPer3()/100)));
        this.setTax4(settlement.getTax4().multiply(BigDecimal.valueOf(park.getPer4()/100)));
        this.setTax7(settlement.getTax7().multiply(BigDecimal.valueOf(park.getPer5()/100)));
    }

    /**
     * 计算客户扶持资金
     * @param settlement 结算单
     * @param contract 客户合同
     */
    public void calculateClient(ViewSettlement1 settlement,Contract contract){
        this.setTax1(settlement.getTax1().multiply(BigDecimal.valueOf(contract.getPer1()/100)));
        this.setTax2(settlement.getTax2().multiply(BigDecimal.valueOf(contract.getPer2()/100)));
        this.setTax3(settlement.getTax3().multiply(BigDecimal.valueOf(contract.getPer3()/100)));
        this.setTax4(settlement.getTax4().multiply(BigDecimal.valueOf(contract.getPer4()/100)));
        this.setTax7(settlement.getTax7().multiply(BigDecimal.valueOf(contract.getPer5()/100)));
    }

    /**
     * 计算园区利润
     * @param d1  政府扶持金
     * @param d2  客户扶持金
     */
    public void calculateProfit(Detail1 d1,Detail1 d2){
        this.setTax1(d1.tax1.subtract(d2.tax1));
        this.setTax2(d1.tax2.subtract(d2.tax2));
        this.setTax3(d1.tax3.subtract(d2.tax3));
        this.setTax4(d1.tax4.subtract(d2.tax4));
        this.setTax7(d1.tax7.subtract(d2.tax7));
    }
}
