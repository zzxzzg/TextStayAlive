package com.guardz.alive.enginer;

import com.guardz.alive.domain.buff.character.CharacterBuff;
import com.guardz.alive.domain.event.weather.WeatherEvent;
import com.guardz.alive.domain.env.time.GameTime;
import com.guardz.alive.domain.env.weather.Weather;
import com.guardz.alive.random.env.buff.DefaultEnvBuffGenerator;
import com.guardz.alive.random.env.weather.WeatherEventDispatcher;
import com.guardz.alive.enginer.Game;
import lombok.Data;
import lombok.ToString;

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

        // 2. 环境事件轮询，生成随机天气事件
        weather.setWeatherEvents(WeatherEventDispatcher.dispatch(weather));

        // 3. 天气事件改变当前环境
        for (WeatherEvent weatherEvent : weather.getWeatherEvents()) {
            weatherEvent.changeEnv();
        }

        game.getGameController().printMessage("---当前天气:" + weather.toString());

        // 4. 根据当前环境，向角色添加buff
        List<CharacterBuff> characterBuffs =  DefaultEnvBuffGenerator.generateCharacterBuff(this);
        game.getCharacter().addBuffs(characterBuffs);
    }
}
