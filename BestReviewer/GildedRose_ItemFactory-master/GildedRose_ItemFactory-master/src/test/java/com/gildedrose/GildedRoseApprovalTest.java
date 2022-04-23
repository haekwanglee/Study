package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseApprovalTest {
    @DisplayName("item list approval test and compare hash code.")
    @Test
    public void testVerifyReport() throws Exception{

        Item[] items = makeItems();
        GildedRose sut = new GildedRose(items);

        StringBuilder results = new StringBuilder();
        for(int day = 1; day <= 100; day++){
            String dayAndItems = Arrays.asList(items).stream().map(n->String.valueOf(n)).collect(Collectors.joining("\t"));
            results.append(dayAndItems);
            results.append("\n");
            sut.updateQuality();
        }

        Approvals.verify(results.toString());

        assertEquals(-788381928,results.toString().hashCode());
    }

    Item[] makeItems(){
        return  new Item[] {
                new NormalItem("+5 Dexterity Vest", 50, 20), //
                new AgedBrie("Aged Brie", 20, 0), //
                new NormalItem("Elixir of the Mongoose", 15, 7), //
                new Sulfurus("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Sulfurus("Sulfuras, Hand of Ragnaros", -1, 80),
                new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 150, 20),
                new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 100, 49),
                new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 75, 49),
                // this conjured item does not work properly yet
                new NormalItem("Conjured Mana Cake", 3, 6) };

    }

}
