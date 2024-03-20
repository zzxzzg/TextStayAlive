package com.guardz.alive.enginer;

import com.guardz.alive.domain.buff.env.EnvBuff;
import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.weather.Weather;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 环境对象
 */
@Data
@ToString
public class Environment {

    private Game game;

    private GameTime gameTime;

    private Weather weather;

    private List<EnvBuff> envBuffs = new ArrayList<>();

    public Environment(Game game) {
        this.game = game;
    }

    public void init() {
        // 1. 初始化时间
        this.gameTime = new GameTime();
        game.getGameController().printMessage("---时间初始化完成");

        game.getGameController().printMessage("--环境初始化完成");
    }

    /**
     * 下一个回合
     */
    public void nextTurn() {
        this.gameTime.nextTurn();
    }

    /**
     * 回合开始前准备
     */
    public void preTurn() {
        // 1. 随机当前天气
        weather = game.getGameMode().getWeatherRandom().random(gameTime, weather);
    }

    public void addBuff(EnvBuff buff) {
        envBuffs.add(buff);
    }

    public void addBuff(List<EnvBuff> buffs) {
        envBuffs.addAll(buffs);
    }
}
