package com.gildedrose;

public class AgedBrieUpdater extends DefaultUpdater {
    @Override
    public void updateQualityAfterChangeSellIn(Item item) {
        if (item.sellIn < 0) {
            increaseItemQulity(item);
        }
    }

    @Override
    public void updateQualityBeforeChangeSellIn(Item item) {
        increaseItemQulity(item);
    }
}
