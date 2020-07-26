package com.yinn.ymall.search.exception;

public class ProductUpException extends RuntimeException{

    private static final long serialVersionUID = -1109000748294070608L;

    public ProductUpException(){
        super("商品上架ES存储失败");
    }
}