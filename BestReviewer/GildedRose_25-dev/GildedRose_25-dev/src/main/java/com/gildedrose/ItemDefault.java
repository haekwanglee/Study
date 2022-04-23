package com.gildedrose;

public class ItemDefault extends Item {

    public ItemDefault(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality > 0) {
            quality -= 1;
        }
    }

    @Override
    protected void updateSellIn() {
        sellIn -= 1;
    }

    @Override
    protected void updateForExpiredSellIn() {
        if (sellIn >= 0) {
            return;
        }
        if (quality > 0) {
            quality -= 1;
        }
    }
}