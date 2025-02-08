package org.wms.security.filter;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wms.common.enums.ErrorCodes;
import org.wms.common.model.Result;
import org.wms.common.model.UserDetails;
import org.wms.common.utils.JWTUtils;
import org.wms.common.utils.RedisUtils;
import org.wms.security.util.SecurityUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * token拦截器
 */
public class TokenFilter extends OncePerRequestFilter {
    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token)) {
            try {
                String userId = JWTUtils.getUserId(token);
                String cacheToken = redisTemplate
                        .opsForValue().get(RedisUtils.TOKEN_KEY + userId);
                if (cacheToken == null || !cacheToken.equals(token)) {
                    throw new BadCredentialsException("Token error");
                }
                UserDetails userDetails = new UserDetails();
                userDetails.setID(userId);
                String rawStr = redisTemplate
                        .opsForValue().get(RedisUtils.PERMISSIONS_KEY + userId);
                List<String> authorities = JSONUtil.toList(rawStr,String.class);
                if (authorities == null) {
                    throw new AccessDeniedException("No permission");
                }

                //将验证过后的用户信息放入context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, SecurityUtil.convertStringToGrantedAuthority(authorities));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException | AuthenticationException e) {
                Result<?> result = Result.error(ErrorCodes.UNAUTHORIZED);
                writeJson(response, result);
                return;
            } catch (AccessDeniedException e) {
                Result<?> result = Result.error(ErrorCodes.FORBIDDEN);
                writeJson(response, result);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private static void writeJson(HttpServletResponse response, Object str) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(str));
        writer.close();
    }
}
