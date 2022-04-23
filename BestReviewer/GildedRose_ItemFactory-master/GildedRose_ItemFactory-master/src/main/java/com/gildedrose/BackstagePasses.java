package com.gildedrose;

public class BackstagePasses extends Item {
    public BackstagePasses(String itemName, int sellin, int quality) {
        super(itemName,sellin,quality);
    }

    @Override
    protected void updateQuality() {
        updateSellIn();
        quality = Math.min(quality+1, 50);
        quality = (sellIn < 12)? Math.min(quality+1, 50): quality;
        quality = (sellIn < 7)? Math.min(quality+1, 50): quality;

        quality = (sellIn < 0)? 0: quality;
    }

    @Override
    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }
}
