package com.vitah.halo.exception;

import com.vitah.halo.constant.CodeEnum;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author vitah
 */
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5927968554058857692L;

    private CodeEnum errorCodeEnum;

    private HttpStatus httpStatus;

    public BusinessException(CodeEnum codeEnum) {
        super(codeEnum.getCode().toString());
        this.errorCodeEnum = codeEnum;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public BusinessException(CodeEnum codeEnum, HttpStatus httpStatus) {
        super(codeEnum.getCode().toString());
        this.errorCodeEnum = codeEnum;
        this.httpStatus = httpStatus;
    }

    public CodeEnum getErrorCodeEnum() {
        return this.errorCodeEnum;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
