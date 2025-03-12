package org.bistu.web.global.exception;

import lombok.Data;
import org.bistu.web.consts.StatusCodeEnum;

@Data
public class SystemException extends RuntimeException{
    private Integer code;

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(StatusCodeEnum infoEnum) {
        super(infoEnum.getMessage());

        this.code = infoEnum.getCode();
    }
}
