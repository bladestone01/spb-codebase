package org.bistu.web.webstore.global.ext;

import lombok.extern.slf4j.Slf4j;
import org.bistu.web.webstore.consts.StatusCodeEnum;
import org.bistu.web.webstore.global.exception.BizException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.bistu.web.webstore.domain.ResultInfo;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultInfo<String> handleException(Exception e) {
        log.error("Global Exception Unhandled", e);
        return ResultInfo.<String>builder().code(StatusCodeEnum.FAILURE.getCode())
                .message(StatusCodeEnum.FAILURE.getMessage()).data(e.getMessage())
                .build();
    }

    @ExceptionHandler(BizException.class)
    public ResultInfo<String> handleSessionInvalidException(BizException e) {
        log.error("Global Exception, BizException", e);
        return ResultInfo.<String>builder().code(e.getCode())
                .message(e.getMessage())
                .build();
    }
}