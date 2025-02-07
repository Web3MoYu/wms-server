package org.wms.common.model;

import lombok.Data;

/**
 * 错误类
 */
@Data
public class ErrorCode {
    Integer code;
    String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
