package com.gildedrose;

public class ItemAgedBrie extends Item {

    public static final String NAME = "Aged Brie";
    public ItemAgedBrie(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        if (quality < 50) {
            quality += 1;
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
        if (quality < 50) {
            quality += 1;
        }
    }
}


