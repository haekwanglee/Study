package com.gildedrose;

public interface ItemUpdater {
    public void updateQualityBeforeChangeSellIn(Item item);
    public void updateQualityAfterChangeSellIn(Item item);
    public void updateSellIn(Item item);
}
