package com.gildedrose;

public class NormalItem extends Item {
    public NormalItem(String itemName, int sellin, int quality) {
        super(itemName, sellin, quality);
    }

    @Override
    protected void updateQuality() {
        updateSellIn();
        quality = Math.max(quality-1, 0);
        quality = (sellIn < 0)? Math.max(quality-1, 0): quality;
    }

    @Override
    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }
}
