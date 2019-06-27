package com.vitah.halo.constant;

/**
 * @author vitah
 */
public enum CodeEnum {

    /**
     * 邮箱已经存在
     */
    EMAIL_IS_EXIST(10001),

    /**
     * 邮箱不存在
     */
    EMAIL_NOT_EXIST(10002),

    /**
     * APP记录不存在
     */
    APP_NOT_EXIST(10101);

    private int code;

    CodeEnum(int errCode) {
        this.code = errCode;
    }

    public Integer getCode() {
        return code;
    }
}
