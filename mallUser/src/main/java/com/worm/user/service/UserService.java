package com.worm.user.service;

import com.worm.service.BaseService;
import com.worm.user.domain.dto.UserDTO;
import com.worm.user.domain.entity.User;

public interface UserService extends BaseService<User> {
    /**
     * 用户登录业务处理
     * @param openid 用户openId
     * @param wx_nickname 用户昵称
     * @param avatar_url 用户头像地址
     * @return
     */
    UserDTO login(String openid, String wx_nickname, String avatar_url);
}
