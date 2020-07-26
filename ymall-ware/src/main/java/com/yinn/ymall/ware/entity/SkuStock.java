package com.yinn.ymall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.experimental.Accessors;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品库存表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-08 01:19:23
 */
@Data
@Accessors(chain = true)
@TableName
public class SkuStock implements Serializable {

    private static final long serialVersionUID = -5644007087797837514L;
    /**
     * 主键
     */
	@TableId
	private Long id;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * 库存数
	 */
	private Integer stock;
	/**
	 * 库存锁定
	 */
	private Integer stockLock;

    public boolean getIsHasStock(){
        return stock>stockLock;
    }

    public boolean IsStockLockSuccess(Integer count){
        return stock - stockLock > count;
    }

}
