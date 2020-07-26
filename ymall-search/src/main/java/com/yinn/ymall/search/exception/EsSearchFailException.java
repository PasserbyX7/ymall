package com.yinn.ymall.search.exception;

public class EsSearchFailException extends RuntimeException{

    private static final long serialVersionUID = 332905050636688097L;

    public EsSearchFailException(){
        super("远程调用ES检索服务失败");
    }
}