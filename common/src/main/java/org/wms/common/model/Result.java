package org.wms.common.model;

import lombok.Data;
import org.wms.common.enums.ErrorCodes;


/**
 * 统一返回结果
 *
 * @param <T>
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>();
        result.code = ErrorCodes.SUCCESS.getCode();
        result.data = data;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }


}