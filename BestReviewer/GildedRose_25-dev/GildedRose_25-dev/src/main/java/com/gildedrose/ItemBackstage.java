package com.gildedrose;

public class ItemBackstage extends Item {


    public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";
    public ItemBackstage(int sellIn, int quality) {
        super(NAME, sellIn, quality);
    }

    @Override
    protected void updateQuality() {
        addQuality();
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
        quality = 0;
    }

    private void addQuality() {
        addQualityOne();
        if (sellIn < 6) {
            addQualityOne();
        }
        if (sellIn < 11) {
            addQualityOne();
        }
    }
    private void addQualityOne() {
        if (quality < 50) {
            quality += 1;
        }
    }
}

