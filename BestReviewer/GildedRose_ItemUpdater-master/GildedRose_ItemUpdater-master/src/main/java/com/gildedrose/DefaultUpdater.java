package com.gildedrose;

public class DefaultUpdater implements ItemUpdater {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    public void updateQualityAfterChangeSellIn(Item item) {
        if (item.sellIn < 0) {
            decreaseItemQulity(item);
        }
    }

    public void updateQualityBeforeChangeSellIn(Item item) {
        decreaseItemQulity(item);
    }

    public void updateSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    protected void increaseItemQulity(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;

        }
    }

    protected void decreaseItemQulity(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - 1;
        }
    }

}
