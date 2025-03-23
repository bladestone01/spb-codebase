package org.bistu.web.webstore.global.exception;

import lombok.Data;

/**
 * @author bladestone
 */
@Data
public class BizException extends Exception {
    private Integer code;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}