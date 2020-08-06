package com.yinn.ymall.common.exception;

public class RPCException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RPCException(String message) {
        super(message);
    }

}