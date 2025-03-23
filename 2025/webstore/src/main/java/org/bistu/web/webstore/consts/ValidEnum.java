package org.bistu.web.webstore.consts;

/**
 * 逻辑删除标识： 1- 有效 0- 无效
 */
public enum ValidEnum {
    VALID(1, "有效"),
    INVALID(0, "无效");

    private Integer code;
    private String message;

    ValidEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
