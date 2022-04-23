package com.gildedrose;

public class AgedBrie extends Item {
    public AgedBrie(String itemName, int sellin, int quality) {
        super(itemName, sellin, quality);
    }

    @Override
    protected void updateQuality() {
        updateSellIn();;
        quality = Math.min(quality+1, 50);
        quality = (sellIn < 0)? Math.min(quality+1, 50): quality;
    }

    @Override
    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }
}
