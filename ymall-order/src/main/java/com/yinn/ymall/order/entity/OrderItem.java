package com.yinn.ymall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;

/**
 * 订单所包含sku信息
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-19 20:41:00
 */
@Data
@Accessors(chain = true)
@TableName
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 6438956219340516701L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * spuId
     */
    private Long spuId;
    /**
     * spu名
     */
    private String spuName;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * skuId
     */
    private Long skuId;
    /**
     * sku名
     */
    private String skuName;
    /**
     * sku图片
     */
    private String skuPic;
    /**
     * 销售属性组合（Json）
     */
    private String skuAttrValues;
    /**
     * 商品购买数量
     */
    private Integer count;
    /**
     * 商品金额
     */
    private BigDecimal skuPrice;
    /**
     * 实付总额
     */
    private BigDecimal payAmount;

    public OrderItem setPayAmount(){
        payAmount=skuPrice.multiply(new BigDecimal(count.toString()));
        return this;
    }

}
