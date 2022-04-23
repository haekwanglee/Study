package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {
    private final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private final String AGED_BRIE = "Aged Brie";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    public void foo() {
        Item[] fooItems = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(fooItems);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    @DisplayName("Aged_brie 의 quality 가 50을 넘지 않는 지 테스트.")
    public void testAgedBrieOver50() throws Exception {
        Item agedBrie = new Item(AGED_BRIE, 10, 50);
        Item[] items = new Item[]{agedBrie};

        GildedRose sut = new GildedRose(items);
        sut.updateQuality();
        assertEquals(50, agedBrie.quality);
    }

    @Test
    @DisplayName("Aged Brie qaulity 의 4일 후 증가 체크 : 유효기간 이전")
    public void checkAgedBrieQuality() {
        int day = 4;
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 20) };
        GildedRose app = new GildedRose(items);
        for(int i=0; i< day; i++)
            app.updateQuality();
        assertEquals(6, app.items[0].sellIn);
        assertEquals(24, app.items[0].quality);
    }

    @Test
    @DisplayName("Aged Brie qaulity 의 4일 후 증가 체크 : 유효기간 이후")
    public void checkAgedBrieItemQualityAferExpiredSellin() {
        int day = 4;
        Item[] items = new Item[] { new Item(AGED_BRIE, 2, 10) };
        GildedRose app = new GildedRose(items);
        for(int i=0; i< day; i++)
            app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    @Test
    @DisplayName("Aged Brie 의 qaulity 최대값 check : 유효기간 이후")
    public void checkAgedBrieIteMaxQualityAferExpiredSellin() {
        int day = 8;
        Item[] items = new Item[] { new Item(AGED_BRIE, 2, 40) };
        GildedRose app = new GildedRose(items);
        for(int i=0; i< day; i++)
            app.updateQuality();
        assertEquals(-6, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    @DisplayName("backstage passes quality : 콘서트일 5일 이전.")
    public void testIncreaseBackStagePassQualityWithin5days() throws Exception {
        Item backStagePass = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 44);
        Item[] items = new Item[]{backStagePass};

        GildedRose sut = new GildedRose(items);
        sut.updateQuality();
        assertEquals(47, backStagePass.quality);
    }

    @Test
    @DisplayName("backstage passes quality 최대값 50 인지 확인.")
    public void testNotIncreaseBackStagePassQualityAbove50() throws Exception {
        Item backStagePassMoreThan10DaysAway = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 50);
        Item backStagePass10DaysAway = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 49);
        Item backStagePass5DaysAway = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 48);

        Item[] passItems = new Item[]{backStagePassMoreThan10DaysAway,backStagePass10DaysAway, backStagePass5DaysAway};

        GildedRose sut = new GildedRose(passItems);
        sut.updateQuality();

        assertAll(()->assertEquals(50, backStagePassMoreThan10DaysAway.quality),
                ()->assertEquals(50, backStagePass10DaysAway.quality),
                ()->assertEquals(50, backStagePass5DaysAway.quality));
    }

    @DisplayName("backstage passes quality 최대값 50 인지 확인. - parameterized test")
    @ParameterizedTest
    @CsvSource({"15, 50","5,49", "5, 48"})
    public void parameterizedTestNotIncreaseBackStagePassQualityAbove50(int sellin, int quality) throws Exception {
        Item passItem = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellin, quality);
        Item[] items = new Item[]{passItem};

        GildedRose sut = new GildedRose(items);
        sut.updateQuality();

        assertEquals(50, passItem.quality);

    }

    @DisplayName("Backstage pass qaulity check : 5일, 7일, 6일, 3일 후")
    @ParameterizedTest
    @CsvSource({"5, 15, 7","7, 13, 9", "6, 14, 8", "3, 17, 5"})
    public void checkBackstagePassQuality(int day, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 20, 2) };
        GildedRose app = new GildedRose(items);
        for(int i=0; i< day; i++)
            app.updateQuality();
        assertEquals(sellIn, app.items[0].sellIn);
        assertEquals(quality, app.items[0].quality);
    }

    @Test
    @DisplayName("Sulfuras qaulity check")
    public void checkSulfurasPassQuality() {
        int day = 5;
        Item[] items = new Item[] { new Item(SULFURAS, 10, 10) };
        GildedRose app = new GildedRose(items);
        for(int i=0; i< day; i++)
            app.updateQuality();
//        assertEquals(5, app.items[0].sellIn); // 가이드에는 유효기간도 고정이라고 되어 있지 않으나 고정이 되었다.
        assertEquals(10, app.items[0].quality);

        day = 7;
        for(int i=0; i< day; i++)
            app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(10, app.items[0].quality);
    }

    @Test
    @DisplayName("다른 Item qaulity 가 50 이상인 경우 확인 => max 처리되지 않았음.")
    public void checkItemInputOverQuality() {
        Item[] items = new Item[] { new Item("Axe", 100, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(99, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality); // 최대 50 이지만 입력 부분에서 max 처리는 안 되어 있다.
    }

    @Test
    @DisplayName("0 이하의 Item qaulity 처리 => min 처리되지 않았음")
    public void checkItemInputMinQuality() {
        Item[] items = new Item[] { new Item("Spear", 5, -1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(-1, app.items[0].quality); // 최소 0 이지만 입력 부분에서 min 처리는 안 되어 있다.
    }

    @Test
    @DisplayName("유효기간이 지난 일반 Item qaulity")
    public void checkItemQualityAferExpiredSellin() {
        int day = 4;
        Item[] items = new Item[] { new Item("Shoes", 2, 50) };
        GildedRose app = new GildedRose(items);
        for(int i=0; i< day; i++)
            app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(44, app.items[0].quality);
    }

}
