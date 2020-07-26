package com.yinn.ymall.coupon.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinn.ymall.coupon.entity.AdImg;

/**
 * 广告表
 *
 * @author Passerby
 * @email 756635176@qq.com
 * @date 2020-07-10 18:21:13
 */
public interface AdImgService extends IService<AdImg> {

    List<AdImg>listByAdCategoryId(Long categoryId);

}

