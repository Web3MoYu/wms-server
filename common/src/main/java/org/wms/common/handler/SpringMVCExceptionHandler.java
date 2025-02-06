package org.wms.common.handler;

import cn.hutool.core.io.resource.NoResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.wms.common.enums.ErrorCodes;
import org.wms.common.model.CommonResult;

@RestControllerAdvice
@Slf4j
public class SpringMVCExceptionHandler {
    @ExceptionHandler(NoResourceException.class)
    public CommonResult<?> NoResourceFoundException(NoResourceFoundException ex) {

        log.error("[NoResourceFoundException] {},\n{}", ex.getResourcePath(), ex);
        return CommonResult.error(ErrorCodes.NOT_FOUND);
    }
}
