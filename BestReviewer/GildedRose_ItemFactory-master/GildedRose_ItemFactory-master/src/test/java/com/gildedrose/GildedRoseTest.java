package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] fooItems = new Item[] {new NormalItem("foo", 0, 0)};
        GildedRose app = new GildedRose(fooItems);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    @DisplayName("Should not increase the quality of aged brie over 50.")
    public void testAgedBrieOver50() throws Exception {
        Item agedBrie = new AgedBrie("Aged Brie", 10, 50);
        Item[] items = new Item[]{agedBrie};

        GildedRose sut = new GildedRose(items);
        sut.updateQuality();
        assertEquals(50, agedBrie.quality);
    }

    @Test
    @DisplayName("Should increase backstage passes quality by 3 when the concert is 5 days or less away.")
    public void testIncreaseBackStagePassQualityWithin5days() throws Exception {
        Item backStagePass = new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 5, 44);
        Item[] items = new Item[]{backStagePass};

        GildedRose sut = new GildedRose(items);
        sut.updateQuality();
        assertEquals(47, backStagePass.quality);
    }

    @Test
    @DisplayName("Should not increase backstage passes above quality of 50.")
    public void testNotIncreaseBackStagePassQualityAbove50() throws Exception {
        Item backStagePassMoreThan10DaysAway = new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 15, 50);
        Item backStagePass10DaysAway = new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        Item backStagePass5DaysAway = new BackstagePasses("Backstage passes to a TAFKAL80ETC concert", 5, 48);

        Item[] passItems = new Item[]{backStagePassMoreThan10DaysAway,backStagePass10DaysAway, backStagePass5DaysAway};

        GildedRose sut = new GildedRose(passItems);
        sut.updateQuality();

        assertAll(()->assertEquals(50, backStagePassMoreThan10DaysAway.quality),
                ()->assertEquals(50, backStagePass10DaysAway.quality),
                ()->assertEquals(50, backStagePass5DaysAway.quality));
    }

    @DisplayName("Should not increase backstage passes above quality of 50. - parameterized test")
    @ParameterizedTest
    @CsvSource({
            "Backstage passes to a TAFKAL80ETC concert,15, 50",
            "Backstage passes to a TAFKAL80ETC concert, 5,49",
            "Backstage passes to a TAFKAL80ETC concert, 5, 48"
    })
    public void parameterizedTestNotIncreaseBackStagePassQualityAbove50(String itemName, int sellin, int quality) throws Exception {
        Item passItem = new ItemFactory().create(itemName, sellin, quality);
        Item[] items = new Item[]{passItem};

        GildedRose sut = new GildedRose(items);
        sut.updateQuality();

        assertEquals(50, passItem.quality);

    }

}
