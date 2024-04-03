package com.guardz.alive.logic.user.controller;

import com.guardz.alive.logic.user.cmd.UserLoginCmd;
import com.guardz.alive.logic.user.executor.UserLoginExecutor;
import com.guardz.alive.logic.user.message.request.UserLoginRequest;
import com.guardz.alive.logic.user.message.response.UserLoginResponse;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * author: wyx
 * date: 2024/4/3
 * description:
 */
@Slf4j
@Component
@ActionController(UserLoginCmd.cmd)
public class UserActionController {

    @Resource
    private UserLoginExecutor userLoginExecutor;

    @ActionMethod(UserLoginCmd.login)
    public UserLoginResponse login(UserLoginRequest userLoginRequest, FlowContext flowContext) {
        // 业务登录
        UserLoginResponse userLoginResponse = userLoginExecutor.login(userLoginRequest);
        // 调用框架登录，建立UserSession
        UserIdSettingKit.settingUserId(flowContext, userLoginResponse.userId);

        return userLoginResponse;
    }

    @ActionMethod(UserLoginCmd.loginOut)
    public void loginOut(FlowContext flowContext) {
        long userId = flowContext.getUserId();
        log.info("用户退出: {}", userId);
    }

}
