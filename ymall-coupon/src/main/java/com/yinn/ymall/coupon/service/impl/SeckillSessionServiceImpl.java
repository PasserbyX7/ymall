package com.yinn.ymall.coupon.service.impl;

import com.yinn.ymall.coupon.entity.SeckillSession;
import com.yinn.ymall.coupon.dao.SeckillSessionDao;
import com.yinn.ymall.coupon.dto.SeckillSessionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.coupon.service.SeckillSessionService;
import com.yinn.ymall.coupon.service.SeckillSkuRelationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀时段表 服务实现类
 * </p>
 *
 * @author Passerby
 * @since 2020-08-13
 */
@Service
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSession>
        implements SeckillSessionService {

    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    @Override
    public List<SeckillSessionDTO> getByDays(Integer days) {
        var start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        var end = LocalDateTime.of(LocalDate.now().plusDays(days - 1), LocalTime.MAX);
        // @formatter:off
        var seckillSessionList = list(
                Wrappers.<SeckillSession>lambdaQuery()
                                .between(SeckillSession::getStartTime, start, end)
        );
        return seckillSessionList.stream()
                                                .map(SeckillSessionDTO::convertFrom)
                                                .map(e->e.setSkus(seckillSkuRelationService.listBySessionId(e.getId())))
                                                .collect(Collectors.toList());
        // @formatter:on
    }

}
