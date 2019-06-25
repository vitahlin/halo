package com.vitah.halo.constant;

/**
 * @author vitah
 */
public enum CodeEnum {

    /**
     * 邮箱已经存在
     */
    EMAIL_IS_EXIST(10001);


    private int code;

    CodeEnum(int errCode) {
        this.code = errCode;
    }

    public Integer getCode() {
        return code;
    }
}
