package com.worm.user.service.impl;

import com.worm.service.impl.BaseServiceImpl;
import com.worm.user.dao.UserMapper;
import com.worm.user.domain.dto.UserDTO;
import com.worm.user.domain.entity.User;
import com.worm.user.service.UserService;
import com.worm.utils.JwtOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    private final UserMapper userMapper;
    private final JwtOperator jwtOperator;

    @Override
    public UserDTO login(String openId, String wxNickname, String avatarUrl) {
        //1：查询用户，判断该用户是否已注册，若没有注册则注册一次
        User user = userMapper.selectOne(
                User.builder()
                        .openId(openId)
                        .build()
        );
        if (user == null) {
            userMapper.insertSelective(
                    User.builder()
                            .openId(openId)
                            .wxNickname(wxNickname)
                            .avatarUrl(avatarUrl)
                            .build()
            );
        }
        //2：获取该用户所有的信息，并将用户id加密，返回给前端
        UserDTO userALlInfo = userMapper.getUserALlInfo(openId);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", userALlInfo.getId());
        String token = jwtOperator.generateToken(userInfo);
        userALlInfo.setId(token);
        return userALlInfo;
    }

}
