package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    ItemUpdater itemUpdater;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item ->{
            itemUpdater = createItemUpdater(item.name);
            itemUpdater.updateQualityBeforeChangeSellIn(item);
            itemUpdater.updateSellIn(item);
            itemUpdater.updateQualityAfterChangeSellIn(item);
        });
    }

    private ItemUpdater createItemUpdater(String itemName) {
        return ItemType.forName(itemName).createItemUpdater();
    }

}