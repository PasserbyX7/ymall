package com.yinn.ymall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yinn.ymall.common.dto.SkuEsDTO;
import com.yinn.ymall.common.utils.Convert;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * spu属性值表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-05-02 02:57:50
 */
@Data
@Accessors(chain = true)
@TableName
public class SpuAttrValue implements Serializable {

    private static final long serialVersionUID = 6333382816621873980L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * spuId
     */
    private Long spuId;
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性值
     */
    private String attrValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否显示
     */
    private Boolean isShow;

    public SkuEsDTO.AttrEsDTO convertToAttrEsDTO() {
        return new SpuAttrValueConvert().doForward(this,SkuEsDTO.AttrEsDTO.class);
    }

    private static class SpuAttrValueConvert implements Convert<SpuAttrValue, SkuEsDTO.AttrEsDTO> {
    }

}
