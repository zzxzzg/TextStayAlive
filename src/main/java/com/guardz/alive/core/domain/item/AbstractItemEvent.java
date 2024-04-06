package com.guardz.alive.core.domain.item;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractItemEvent implements ItemEvent{
    private final Item item;

    @Getter
    @Setter
    private ItemStatus itemStatus;

    public AbstractItemEvent(Item item){
        this.item = item;
    }

}
