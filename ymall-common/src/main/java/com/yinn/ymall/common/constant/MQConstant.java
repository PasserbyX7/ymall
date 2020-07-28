package com.yinn.ymall.common.constant;

public class MQConstant {

    public static class Order {
        public static final String TOPIC = "order";
        public static final String ORDER_CLOSE_CONSUMER_GROUP = "order-close";
        public static final String ORDER_PAID_CONSUMER_GROUP = "order-paid";
        public static final String TAG_CLOSE = "close";
        public static final String TAG_PAID = "paid";
        /**
         * 延迟十分钟
         */
        public static final int ORDER_CLOSE_DELAY_TIME = 14;
    }

    public static class Ware {
        public static final String TOPIC = "ware";
        public static final String WARE_UNLOCK_CONSUMER_GROUP = "ware-unlock";
        public static final String TAG_UNLOCK = "unlock";
        /**
         * 延迟半小时
         */
        public static final int WARE_UNLOCK_DELAY_TIME = 16;
    }

    public static String getTopicAndTag(String topic,String tag){
        return String.format("%s:%s", topic,tag);
    }
}