package org.bistu.web.webstore.domain;

import lombok.Builder;
import lombok.Data;
import org.bistu.web.webstore.consts.StatusCodeEnum;

@Data
@Builder
public class ResultInfo<T> {
    private Integer code;
    private String message;
    private T data;


    public ResultInfo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultInfo success(T data) {
        return ResultInfo.builder()
                .code(StatusCodeEnum.SUCCESS.getCode())
                .message("success")
                .data(data)
                .build();
    }

    public static <T> ResultInfo failure(T data) {
        return ResultInfo.builder()
                .code(StatusCodeEnum.FAILURE.getCode())
                .message(StatusCodeEnum.FAILURE.getMessage())
                .data(data)
                .build();
    }
}