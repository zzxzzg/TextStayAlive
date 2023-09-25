package com.guardz.alive.domain.character;

import com.guardz.alive.domain.item.Item;
import com.guardz.alive.domain.item.ItemStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 背包
 */
@Data
public class Bag {
    private List<Item> items = new ArrayList<>();

    private Character character;

    public Bag(Character character) {
        this.character = character;
    }

    public void init() {
    }

    public void preTurn() {
        items.forEach(item -> item.getItemEvent().onItemPreTurn(getCharacter().getGame()));
        items.removeIf(item -> ItemStatus.STATUS_DESTROY == item.getItemEvent().getItemStatus());
    }
}
