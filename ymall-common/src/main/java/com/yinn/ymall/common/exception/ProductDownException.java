package com.yinn.ymall.common.exception;

public class ProductDownException extends RuntimeException{
    private static final long serialVersionUID = -1109000748294070608L;

    public ProductDownException(){
        super("商品下架ES删除失败");
    }
}