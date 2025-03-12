package org.bistu.web.consts;

import lombok.Getter;

@Getter
public enum StatusCodeEnum {
    SUCCESS(0, "success"),
    FAILURE(10000,  "failure"),

    //10000 ~ 110000: 系统级错误码
    INTERNAL_ERROR(100001, "未知系统错误"),
    INVALID_PRODUCT_ID(10002, "无效的商品ID");

    private Integer code;
    private String message;

    StatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
