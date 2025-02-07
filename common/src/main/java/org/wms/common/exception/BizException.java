package org.wms.common.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class BizException extends RuntimeException {
    private final Integer code;

    public BizException() {
        this("系统异常");
    }

    public BizException(String message) {
        super(message);
        this.code = 500;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}