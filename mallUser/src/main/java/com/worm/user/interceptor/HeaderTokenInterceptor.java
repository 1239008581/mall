package com.worm.user.interceptor;

import com.worm.user.handler.exception.SecurityException;
import com.worm.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class HeaderTokenInterceptor extends HandlerInterceptorAdapter {

    private final JwtOperator jwtOperator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SecurityException {
        try{
            //获取token，并判断token是否已过期
            String token = request.getHeader("X-Token");
            if(token.equals("test")){
                //测试
                request.setAttribute("userId",1);
                return true;
            }
            if (!jwtOperator.validateToken(token)) {
                log.error("token解析错误！");
                throw new SecurityException("token解析错误！");
            }
            //获取token的信息并且写入request
            Claims claims = jwtOperator.getClaimsFromToken(token);
            Integer userId = (Integer) claims.get("userId");
            request.setAttribute("userId",userId);
        }catch(Throwable throwable){
            log.error(throwable.getMessage());
            throw new SecurityException(throwable.getMessage());
        }
        return true;
    }
}
