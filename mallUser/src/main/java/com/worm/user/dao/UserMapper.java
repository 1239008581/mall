package com.worm.user.dao;

import com.worm.user.domain.dto.UserDTO;
import com.worm.user.domain.entity.User;
import com.worm.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {
    UserDTO getUserALlInfo(String openId);

}