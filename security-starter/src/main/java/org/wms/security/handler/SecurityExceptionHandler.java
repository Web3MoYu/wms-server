package org.wms.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wms.common.enums.ErrorCodes;
import org.wms.common.model.CommonResult;
import org.wms.common.utils.JWTUtils;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class SecurityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException e) {
        String token = req.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            log.warn("[authenticationExceptionHandler][ 无法访问 url({})]",
                    req.getRequestURL(), e);
            return CommonResult.error(ErrorCodes.UNAUTHORIZED);
        }
        log.warn("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", JWTUtils.getUserId(token),
                req.getRequestURL(), e);
        return CommonResult.error(ErrorCodes.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public CommonResult<?> authenticationExceptionHandler(HttpServletRequest req, AuthenticationException e) {
        log.warn("[authenticationExceptionHandler][ 无法访问 url({})]",
                req.getRequestURL(), e);
        return CommonResult.error(ErrorCodes.UNAUTHORIZED);
    }
}
