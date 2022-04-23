package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TexttestFixture {

    @Test
    @DisplayName("Golden Master Test")
    public void goldenMasterTest() {
        System.out.println("OMGHAI!");

        Item[] items = new Item[] {
                Item.getItem("+5 Dexterity Vest", 10, 20), //
                Item.getItem("Aged Brie", 2, 0), //
                Item.getItem("Elixir of the Mongoose", 5, 7), //
                Item.getItem("Sulfuras, Hand of Ragnaros", 0, 80), //
                Item.getItem("Sulfuras, Hand of Ragnaros", -1, 80),
                Item.getItem("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                Item.getItem("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                Item.getItem("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                Item.getItem("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 2;
        String TAG = "goldenMasterTest";
        List<String> testResults = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            testResults.add("-------- day " + i + " --------");
            testResults.add("name, sellIn, quality");
            for (Item item : items) {
                testResults.add(item.toString());
            }
            testResults.add("");
            app.updateQuality();
        }
        Approvals.verifyAll(TAG, testResults.toArray());
    }

}
