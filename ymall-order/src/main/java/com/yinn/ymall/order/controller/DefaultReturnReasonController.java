package com.yinn.ymall.order.controller;

import java.util.List;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.order.entity.DefaultReturnReason;
import com.yinn.ymall.order.service.DefaultReturnReasonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "预设退货原因接口")
@RestController
@RequestMapping("/api/order/v1/default-return-reasons")
public class DefaultReturnReasonController {

    @Autowired
    private DefaultReturnReasonService defaultReturnReasonService;

    @ApiOperation("原因列表")
    @GetMapping
    public R<List<DefaultReturnReason>> list(){
        return R.ok(defaultReturnReasonService.list());
    }

    @ApiOperation("原因查询")
    @GetMapping("/{defaultReturnReasonId}")
    public R<DefaultReturnReason> getById(@PathVariable Long defaultReturnReason){
        return R.ok(defaultReturnReasonService.getById(defaultReturnReason));
    }

    @ApiOperation("原因新增")
    @PostMapping
    public R<Void> save(@RequestBody DefaultReturnReason defaultReturnReason) {
        defaultReturnReasonService.save(defaultReturnReason);
        return R.ok();
    }

    @ApiOperation("原因修改")
    @PutMapping
    public R<Void> update(@RequestBody DefaultReturnReason defaultReturnReason) {
        defaultReturnReasonService.updateById(defaultReturnReason);
        return R.ok();
    }

    @ApiOperation("原因删除")
    @DeleteMapping("/{defaultReturnReasonId}")
    public R<Void> remove(@PathVariable Long defaultReturnReasonId) {
        defaultReturnReasonService.removeById(defaultReturnReasonId);
        return R.ok();
    }

}