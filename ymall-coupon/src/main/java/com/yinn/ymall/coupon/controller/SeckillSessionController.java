package com.yinn.ymall.coupon.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.coupon.entity.SeckillSession;
import com.yinn.ymall.coupon.service.SeckillSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 秒杀时段表 前端控制器
 * </p>
 *
 * @author Passerby
 * @since 2020-08-13
 */
@Api(tags = "秒杀时段接口")
@RestController
@RequestMapping("/api/coupon/v1/seckill-sessions")
public class SeckillSessionController {

    @Autowired
    private SeckillSessionService seckillSessionService;

    @ApiOperation("秒杀时段查询")
    @GetMapping("/{seckillSessionId}")
    public R<SeckillSession> getById(@PathVariable Long seckillSessionId) {
        return R.ok(seckillSessionService.getById(seckillSessionId));
    }

    @ApiOperation("最近n天秒杀时段查询")
    @GetMapping("/days/{days}")
    public R<List<SeckillSession>> getByDays(@PathVariable Integer days) {
        return R.ok(seckillSessionService.getByDays(days));
    }

    @ApiOperation("秒杀时段新增")
    @PostMapping
    public R<Void> save(@RequestBody SeckillSession seckillSession) {
        seckillSessionService.save(seckillSession);
        return R.ok();
    }

    @ApiOperation("秒杀时段修改")
    @PutMapping
    public R<Void> update(@RequestBody SeckillSession seckillSession) {
        seckillSessionService.updateById(seckillSession);
        return R.ok();
    }

    @ApiOperation("秒杀时段删除")
    @DeleteMapping("/{seckillSessionId}")
    public R<Void> remove(@PathVariable Long seckillSessionId) {
        seckillSessionService.removeById(seckillSessionId);
        return R.ok();
    }
}
