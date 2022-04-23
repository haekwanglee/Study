package com.gildedrose;

public class ItemFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    public static Item create(String itemName, int sellin, int quality) {
        switch (itemName) {
            case AGED_BRIE:
                return new AgedBrie(itemName, sellin, quality);
            case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                return new BackstagePasses(itemName, sellin, quality);
            case SULFURAS_HAND_OF_RAGNAROS:
                return new Sulfurus(itemName, sellin, quality);
            default:
                return new NormalItem(itemName, sellin, quality);
        }
    }
}
