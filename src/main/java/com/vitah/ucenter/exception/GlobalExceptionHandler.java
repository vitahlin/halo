package com.vitah.ucenter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author vitah
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
        BusinessException.class
    })
    public ResponseEntity<Object> handlerException(Exception ex, HttpServletRequest req) {

        if (ex instanceof BusinessException) {
            // 自定义的业务异常
            return genErrorInfoForBusinessException((BusinessException) ex);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 生成业务错误处理信息
     *
     * @param ex
     * @return
     */
    private ResponseEntity<Object> genErrorInfoForBusinessException(BusinessException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("code", ex.getErrorCodeEnum().getCode());
        body.put("message", ex.getErrorCodeEnum().toString());

        return new ResponseEntity<>(body, ex.getHttpStatus());
    }
}
