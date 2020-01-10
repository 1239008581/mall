package com.worm.user.interceptor;

import com.worm.constant.UserConstant;
import com.worm.user.handler.exception.SecurityException;
import com.worm.utils.JwtOperator;
import com.worm.utils.RedisOperator;
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
    private final RedisOperator redisOperator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SecurityException {
        try{
            //获取token
            String token = request.getHeader("X-Token");

            //测试
            if(token.equals("test")){
                request.setAttribute("userId",1);
                return true;
            }

            //判断token是否无效或已过期
            if (!jwtOperator.validateToken(token)) {
                throw new SecurityException("token解析错误！");
            }

            //解密token,获取token信息
            Claims claims = jwtOperator.getClaimsFromToken(token);
            String key = (String) claims.get("key");

            //从redis中获取获取userId，并判断用户是否已注销
            Integer userId = (Integer) redisOperator.get(key);
            if (userId == null) {
                throw new SecurityException("用户缓存已失效，请重新登录！");
            }

            //将userId写入request
            request.setAttribute("userId",userId);
        }catch(Throwable throwable){
            log.error(throwable.getMessage());
            throw new SecurityException(throwable.getMessage());
        }
        return true;
    }
}
