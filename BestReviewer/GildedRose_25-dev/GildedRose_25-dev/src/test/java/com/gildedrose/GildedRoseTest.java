package com.gildedrose;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GildedRoseTest {

    @Test
    @DisplayName("foo test")
    public void foo() {
        Item[] items = new Item[] { Item.getItem("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @DisplayName("유효기간 내의 일반 Item의 퀄리티가 하루가 지날때마다 1씩 줄어듬")
    public void testNormalItemQuality() {
        int startQuality = 50;
        Item[] items = new Item[] { Item.getItem("foo", 50, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 50; day++) {
            int targetQuality = startQuality - day;
            assertEquals(targetQuality, app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Item의 퀄리티는 0 이상")
    public void testQualityShouldBeLagerThanZero() {
        int startQuality = 50;
        Item[] items = new Item[] { Item.getItem("foo", 50, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 55; day++) {
            int targetQuality = startQuality - day;
            assertEquals(Math.max(0, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("유통기한이 지난 Item의 퀄리티는 두배의 속도로 떨어짐")
    public void testExpiredItemQuality() {
        int startQuality = 50;
        Item[] items = new Item[] { Item.getItem("foo", -1, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 50; day++) {
            int targetQuality = startQuality - day * 2;
            assertEquals(Math.max(0, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Aged Brie는 하루가 지날 때마다 퀄리티가 1씩 증가")
    public void testAgedBrieItemQuality() {
        int startQuality = 25;
        Item[] items = new Item[] { Item.getItem("Aged Brie", 50, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 50; day++) {
            int targetQuality = startQuality + day;
            assertEquals(Math.min(50, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Aged Brie는 유통기한이 지날 경우 하루가 지날 때마다 퀄리티가 2씩 증가")
    public void testExpiredAgedBrieItemQuality() {
        int startQuality = 25;
        Item[] items = new Item[] { Item.getItem("Aged Brie", -1, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 50; day++) {
            int targetQuality = startQuality + day * 2;
            assertEquals(Math.min(50, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Backstage Pass는 유통기한이 11일 이상일 때는 하루에 1씩 퀄리티 증가")
    public void test11BackstagePassItemQuality() {
        int startQuality = 25;
        int sellIn = 15;
        int spendDay = 0;
        Item[] items = new Item[] { Item.getItem("Backstage passes to a TAFKAL80ETC concert", sellIn, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = sellIn; day > 11; day--, spendDay++) {
            int targetQuality = startQuality + spendDay;
            assertEquals(Math.min(50, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Backstage Pass는 유통기한이 10일 이하 5일 이상일때 하루에 2씩 퀄리티 증가")
    public void test10to6BackstagePassItemQuality() {
        int startQuality = 25;
        int sellIn = 10;
        int spendDay = 0;
        Item[] items = new Item[] { Item.getItem("Backstage passes to a TAFKAL80ETC concert", sellIn, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = sellIn; day > 5; day--, spendDay++) {
            int targetQuality = startQuality + spendDay * 2;
            assertEquals(Math.min(50, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Backstage Pass는 유통기한이 5이하 일때 하루에 3씩 퀄리티 증가")
    public void test5to1BackstagePassItemQuality() {
        int startQuality = 25;
        int sellIn = 5;
        int spendDay = 0;
        Item[] items = new Item[] { Item.getItem("Backstage passes to a TAFKAL80ETC concert", sellIn, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = sellIn; day > 0; day--, spendDay++) {
            int targetQuality = startQuality + spendDay * 3;
            assertEquals(Math.min(50, targetQuality), app.items[0].quality);
            app.updateQuality();
        }
    }

    @Test
    @DisplayName("Backstage Pass는 유통기한이 지나면 퀄리티는 0이 됨")
    public void testExpiredBackstagePassItemQuality() {
        int startQuality = 25;
        int sellIn = 5;
        Item[] items = new Item[] { Item.getItem("Backstage passes to a TAFKAL80ETC concert", sellIn, startQuality) };

        GildedRose app = new GildedRose(items);
        assertEquals(startQuality, app.items[0].quality);

        for (int day = 0; day < sellIn; day++) {
            app.updateQuality();
            assertNotEquals(0, app.items[0].quality);
        }

        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @DisplayName("Sulfuras의 퀄리티는 변경되지 않음")
    public void testSulfurasItemQuality() {
        int startQuality = 50;
        Item[] items = new Item[] { Item.getItem("Sulfuras, Hand of Ragnaros", 50, startQuality) };
        GildedRose app = new GildedRose(items);
        for (int day = 0; day < 50; day++) {
            assertEquals(startQuality, app.items[0].quality);
            app.updateQuality();
        }
    }

}
