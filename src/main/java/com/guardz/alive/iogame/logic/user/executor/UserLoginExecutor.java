package com.guardz.alive.iogame.logic.user.executor;

import com.guardz.alive.iogame.logic.user.message.request.UserLoginRequest;
import com.guardz.alive.iogame.logic.user.message.response.UserLoginResponse;
import org.springframework.stereotype.Component;

/**
 * author: wyx
 * date: 2024/4/3
 * description:
 */
@Component
public class UserLoginExecutor {

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.userId = 1001L;
        userLoginResponse.message = "欢迎你 " + userLoginRequest.name + ", 可怜的求生者.";
        return userLoginResponse;
    }

}
