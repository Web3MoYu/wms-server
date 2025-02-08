package org.wms.common.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.wms.common.enums.ErrorCodes;
import org.wms.common.exception.BizException;
import org.wms.common.model.Result;

/**
 * 公共异常处理
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
    private String applicationName;

    public CommonExceptionHandler(String applicationName) {
        this.applicationName = applicationName;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.error("[missingServletRequestParameterExceptionHandler]:{}", ex.getMessage());
        return Result.error(ErrorCodes.BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("[methodArgumentNotValidExceptionExceptionHandler]:{}", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return Result.error(ErrorCodes.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(BindException ex) {
        log.error("[handleBindException]:{}", ex.getMessage());
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null; // 断言，避免告警
        return Result.error(ErrorCodes.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.error("[constraintViolationExceptionHandler]:{}", ex.getMessage());
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return Result.error(ErrorCodes.BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", constraintViolation.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public Result<?> validationException(ValidationException ex) {
        log.error("[constraintViolationExceptionHandler]:{}", ex.getMessage());
        return Result.error(ErrorCodes.BAD_REQUEST);
    }

    @ExceptionHandler(BizException.class)
    public Result<?> bizExceptionHandler(BizException ex) {
        log.error("[bizExceptionHandler]:{}", ex.getMessage());
        return Result.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> defaultExceptionHandler(Exception ex) {
        log.error("[defaultExceptionHandler]:{}", ex.getMessage());
        return new ResponseEntity<>(
                Result.error(ErrorCodes.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
