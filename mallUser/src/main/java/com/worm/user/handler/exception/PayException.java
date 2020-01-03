package com.worm.user.handler.exception;

public class PayException extends RuntimeException {

    public PayException(String message){
        super("支付错误！"+message);
    }

}
