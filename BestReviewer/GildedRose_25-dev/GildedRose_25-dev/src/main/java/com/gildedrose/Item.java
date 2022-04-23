package com.gildedrose;

public abstract class Item {
    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    //TODO: item updater 필요
    public void updateItem() {
        updateQuality();
        updateSellIn();
        updateForExpiredSellIn();
    }

    protected abstract void updateQuality();
    protected abstract void updateSellIn();
    protected abstract void updateForExpiredSellIn();

    public static Item getItem(String name, int sellIn, int quality) {
        switch (name) {
            case ItemAgedBrie.NAME: {
                return new ItemAgedBrie(sellIn, quality);
            }
            case ItemBackstage.NAME: {
                return new ItemBackstage(sellIn, quality);
            }
            case ItemSulfuras.NAME: {
                return new ItemSulfuras(sellIn, quality);
            }
            default: {
                return new ItemDefault(name, sellIn, quality);
            }
        }
    }
}
