package org.bistu.web.global.exception;

import lombok.Data;
import org.bistu.web.consts.StatusCodeEnum;

/**
 *
 */
@Data
public class BizException extends Exception {
    private Integer code;

    public BizException(StatusCodeEnum infoEnum) {
        super(infoEnum.getMessage());

        this.code = infoEnum.getCode();
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
