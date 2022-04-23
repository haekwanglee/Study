package com.gildedrose;

public class BackstagePassesUpdater extends DefaultUpdater {
    @Override
    public void updateQualityAfterChangeSellIn(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    @Override
    public void updateQualityBeforeChangeSellIn(Item item) {
        increaseItemQulity(item);
        if (item.sellIn < 11) {
            increaseItemQulity(item);
        }
        if (item.sellIn < 6) {
            increaseItemQulity(item);
        }
    }
}
