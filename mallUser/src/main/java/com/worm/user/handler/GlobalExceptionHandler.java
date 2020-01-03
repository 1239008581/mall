package com.worm.user.handler;

import com.worm.user.handler.exception.PayException;
import com.worm.utils.JsonResult;
import com.worm.user.handler.exception.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SecurityException.class)
    public JsonResult error(){
        log.error("用户权限不足，无法访问！");
        return JsonResult.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .msg("抱歉！您无权访问，请登录后再访问！")
                .data(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(PayException.class)
    public JsonResult payError(PayException e){
        log.error(e.getMessage());
        return JsonResult.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(e.getMessage())
                .data(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult error(IllegalArgumentException e){
        log.error(e.getMessage());
        return JsonResult.builder()
                .status(HttpStatus.EXPECTATION_FAILED.value())
                .msg(e.getMessage())
                .data(HttpStatus.EXPECTATION_FAILED)
                .build();
    }

    @ExceptionHandler(Exception.class)
    public JsonResult error(Exception e){
        log.error(e.getMessage());
        return JsonResult.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(e.getMessage())
                .data(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

}
