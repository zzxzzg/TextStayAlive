package com.guardz.alive.core.domain.item.food;

import com.guardz.alive.core.enginer.Game;
import com.guardz.alive.core.domain.env.time.GameTime;
import com.guardz.alive.core.domain.item.AbstractItemEvent;
import com.guardz.alive.core.domain.item.Item;
import com.guardz.alive.core.domain.item.ItemEvent;
import com.guardz.alive.core.domain.item.ItemStatus;
import lombok.Data;

/**
 * 道具：面包
 */
@Data
public class Bread implements Item {
    private BreadEvent breadEvent = new BreadEvent(this);

    /**
     * 保质期，天
     */
    private static final int QUALITY_GUARANTEE_PERIOD = 10;

    @Override
    public ItemEvent getItemEvent() {
        return breadEvent;
    }

    private static class BreadEvent extends AbstractItemEvent {
        /**
         * 道具获取时间
         */
        private GameTime getTime;
        public BreadEvent(Item item) {
            super(item);
        }

        @Override
        public void onItemGet(Game game) {
            setItemStatus(ItemStatus.STATUS_NORMAL);
        }

        @Override
        public void onItemUse(Game game) {

        }

        @Override
        public void onItemDestroy(Game game) {
            setItemStatus(ItemStatus.STATUS_DESTROY);
        }

        @Override
        public void onItemPreTurn(Game game) {
            int day = game.getEnvironment().getGameTime().afterDay(getTime);
            // 过期销毁
            if (day > QUALITY_GUARANTEE_PERIOD) {
                setItemStatus(ItemStatus.STATUS_DESTROY);
            }
        }

        @Override
        public void onItemPostTurn(Game game) {

        }
    }
}
