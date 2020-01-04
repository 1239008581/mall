package com.worm.user.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.worm.user.domain.dto.UserDTO;
import com.worm.user.service.UserService;
import com.worm.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户相关功能API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserController {

    private final WxMaService wxMaService;
    private final UserService userService;

    @GetMapping("/login")
    @ApiOperation("用户登录API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "微信端传来的code码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wxNickname", value = "微信端传来的用户昵称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "avatarUrl", value = "微信端传来的用户头像地址", dataType = "String", paramType = "query")
    })
    public JsonResult login(String code, String wxNickname, String avatarUrl) throws WxErrorException {
        WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
        String openId = sessionInfo.getOpenid();
        UserDTO userDTO = userService.login(openId, wxNickname, avatarUrl);
        return JsonResult.ok(userDTO);
    }

}
