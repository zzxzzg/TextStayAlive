package com.guardz.alive.domain.env.time;

import lombok.Data;

/**
 * 游戏时间
 * 设定游戏时间 一年 4季， 每个季度 30天 ， 每天 3 个回合
 */
@Data
public class GameTime implements Cloneable {
    private Season season;

    /**
     * 年
     */
    private int year;

    /**
     * 日期， 1~30
     */
    private int day;

    /**
     * 回合， 1~3
     * 1 = Morning, 2 = Afternoon, 3 = Evening
     */
    private int turn;

    public GameTime() {
        init();
    }

    private void init() {
        this.season = Season.SPRING;
        this.year = 1;
        this.day = 1;
        this.turn = 1;
    }

    public void nextTurn() {
        this.turn++;
        if (this.turn > 3) {
            this.turn = 1;
            nextDay();
        }
    }

    /**
     * 时间比较，当前时间 - gameTime
     */
    public int afterDay(GameTime gameTime) {
        return (year - gameTime.year) * 30 * 4 + (season.getCode() - gameTime.season.getCode()) * 30 + (this.day - gameTime.day);
    }

    public int weatherDay() {
        return ((season.getCode() - Season.SPRING.getCode()) * 30 + day + 14) % (Season.WINTER.getCode() * 30);
    }

    private void nextDay() {
        this.day++;
        if (this.day > 30) {
            this.day = 1;
            nextSeason();
        }
    }

    private void nextSeason() {
        switch (this.season) {
            case SPRING:
                this.season = Season.SUMMER;
                break;
            case SUMMER:
                this.season = Season.AUTUMN;
                break;
            case AUTUMN:
                this.season = Season.WINTER;
                break;
            case WINTER:
                this.season = Season.SPRING;
                year = year + 1;
                break;
        }
    }

    public String toString() {
        String str;
        if (turn == 1) {
            str = "早上";
        } else if (turn == 2) {
            str = "下午";
        } else {
            str = "晚上";
        }
        return year + "年 " + season.getName() + " " + day + "日 " + str;
    }

    @Override
    public GameTime clone() {
        try {
            return (GameTime) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
