package com.yinn.ymall.order.constant;

public class OrderSettingConstant {
    /**
	 * 秒杀订单超时时间（以分为单位）
	 */
	public static final Integer FLASH_ORDER_OVERTIME=10;
	/**
	 * 普通订单超时时间（以分为单位）
	 */
	public static final Integer NORMAL_ORDER_OVERTIME=30;
	/**
	 * 发货后自动确认收货时间（以天为单位）
	 */
	public static final Integer AUTO_CONFIRM_RECEIPT_TIME=30;
	/**
	 * 订单完成后自动好评时间（以天为单位）
	 */
	public static final Integer AUTO_COMMENT_TIME=7;
	/**
	 * 自动完成交易且无法申请售后时间（以天为单位）
	 */
	public static final Integer ORDER_FINISH_TIME=365;
}