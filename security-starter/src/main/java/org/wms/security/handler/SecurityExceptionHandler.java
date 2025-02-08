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
import org.wms.common.model.Result;
import org.wms.common.utils.JWTUtils;

/**
 * security异常拦截器
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class SecurityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException e) {
        String token = req.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            log.error("[authenticationExceptionHandler][ 无法访问 url({})],{}",
                    req.getRequestURL(), e.getMessage());
            return Result.error(ErrorCodes.UNAUTHORIZED);
        }
        log.error("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]:{}", JWTUtils.getUserId(token),
                req.getRequestURL(), e.getMessage());
        return Result.error(ErrorCodes.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result<?> authenticationExceptionHandler(HttpServletRequest req, AuthenticationException e) {
        log.error("[authenticationExceptionHandler][ 无法访问 url({})]:{}",
                req.getRequestURL(), e.getMessage());
        return Result.error(ErrorCodes.UNAUTHORIZED);
    }
}
