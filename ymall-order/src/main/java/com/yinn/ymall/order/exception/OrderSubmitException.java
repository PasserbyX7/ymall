package com.yinn.ymall.order.exception;

public class OrderSubmitException extends RuntimeException{

    private static final long serialVersionUID = 7792374379137565173L;

    public OrderSubmitException() {
        super();
    }

    public OrderSubmitException(String message) {
        super(message);
    }

}