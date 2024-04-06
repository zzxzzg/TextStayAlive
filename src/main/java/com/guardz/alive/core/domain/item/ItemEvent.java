package com.guardz.alive.core.domain.item;

import com.guardz.alive.core.enginer.Game;

/**
 * 道具事件
 */
public interface ItemEvent {

    ItemStatus getItemStatus();

    /**
     * 当获得道具
     */
    void onItemGet(Game game);

    /**
     * 使用道具
     */
    void onItemUse(Game game);

    /**
     * 销毁道具
     */
    void onItemDestroy(Game game);

    /**
     * 道具每回合前触发效果
     */
    void onItemPreTurn(Game game);

    /**
     * 道具每回合后触发效果
     */
    void onItemPostTurn(Game game);
}
