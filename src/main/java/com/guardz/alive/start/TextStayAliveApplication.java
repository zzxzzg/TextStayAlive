package com.guardz.alive.start;

import com.guardz.alive.external.hook.AliveUserHook;
import com.guardz.alive.logic.user.UserLogicService;
import com.guardz.alive.start.logic.CommonLogicServer;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.external.core.ExternalServer;
import com.iohao.game.external.core.netty.DefaultExternalCoreSetting;
import com.iohao.game.external.core.netty.DefaultExternalServer;
import com.iohao.game.external.core.netty.DefaultExternalServerBuilder;
import com.iohao.game.external.core.netty.simple.NettyRunOne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.guardz.alive")
public class TextStayAliveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextStayAliveApplication.class, args);

        // 游戏对外服端口
        ExternalServer externalServer = createExternalServer();

        // spring 逻辑服
        UserLogicService userLogicService = new UserLogicService();

        // 全局配置
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());

        // 启动 对外服、网关服、逻辑服; 并生成游戏业务文档
        // 简单的启动器
        new NettyRunOne()
            // 游戏对外服
            .setExternalServer(externalServer)
            // 游戏逻辑服列表
            .setLogicServerList(List.of(userLogicService))
            // 启动 对外服、网关、逻辑服
            .startup();
    }

    private static ExternalServer createExternalServer(){
        int port = 10100;
        // 游戏对外服 - 构建器
        DefaultExternalServerBuilder builder = DefaultExternalServer.newBuilder(port);
        // ExternalCore 设置项
        DefaultExternalCoreSetting setting = builder.setting();
        // 设置 自定义 用户上线、下线的钩子
        setting.setUserHook(new AliveUserHook());
        // 创建对外服
        return builder.build();
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        // 将业务框架交给 spring 管理
        return ActionFactoryBeanForSpring.me();
    }
}
