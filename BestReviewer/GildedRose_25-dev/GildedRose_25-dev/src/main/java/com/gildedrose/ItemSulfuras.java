package com.gildedrose;

public class ItemSulfuras extends Item {

    public static final String NAME = "Sulfuras, Hand of Ragnaros";
    public ItemSulfuras(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    @Override
    protected void updateQuality() {

    }

    @Override
    protected void updateSellIn() {

    }

    @Override
    protected void updateForExpiredSellIn() {

    }
}
