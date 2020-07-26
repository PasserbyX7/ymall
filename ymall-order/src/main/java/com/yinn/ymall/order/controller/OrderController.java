package com.yinn.ymall.order.controller;

import com.yinn.ymall.common.api.R;
import com.yinn.ymall.common.utils.JwtUtils;
import com.yinn.ymall.order.constant.OrderStatusEnum;
import com.yinn.ymall.order.dto.OrderConfirmDTO;
import com.yinn.ymall.order.dto.OrderSubmitDTO;
import com.yinn.ymall.order.dto.PrePaymentDTO;
import com.yinn.ymall.order.entity.Order;
import com.yinn.ymall.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "订单接口")
@RestController
@RequestMapping("/api/order/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("获得订单确认页信息")
    @GetMapping("/order-confirm")
    public R<OrderConfirmDTO>list(@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long id){
        return R.ok(orderService.getOrderConfirmDTO(id));
    }

    @ApiOperation("订单号查询订单")
    @GetMapping("/{orderSn}")
    public R<Order>getBySn(@PathVariable String orderSn){
        return R.ok(orderService.getByOrderSn(orderSn));
    }

    @ApiOperation("订单状态查询订单")
    @GetMapping("/order-status/{orderStatus}")
    public R<Order>getByOrderStatus(@AuthenticationPrincipal(expression = JwtUtils.GET_USER_ID_EXPR) Long userId,
                                                            @PathVariable OrderStatusEnum orderStatus){
        return R.ok(orderService.getByOrderStatus(userId, orderStatus));
    }

    @ApiOperation("订单提交")
    @PostMapping
    public R<Void>submit(OrderSubmitDTO orderSubmitDTO){
        orderService.submit(orderSubmitDTO);
        return R.ok();
    }

    @ApiOperation("微信小程序支付")
    @PostMapping("/{orderSn}/actions/payment")
    public R<PrePaymentDTO>wxMiniPay(@AuthenticationPrincipal(expression = JwtUtils.GET_USER_OPENID_EXPR) String openId,
                                                                    @PathVariable String orderSn){
        return R.ok(orderService.wxMiniPay(orderSn,openId));
    }

    @ApiOperation("订单关闭")
    @PutMapping("/{orderSn}/actions/close")
    public R<Void>close(@PathVariable String orderSn){
        orderService.closeOrder(orderSn);
        return R.ok();
    }

}