/**
 * Copyright 2020 by Samsung Electronics, Inc.,
 *
 * This software is the confidential and proprietary information
 * of Samsung Electronics, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Samsung.
 */

package com.bestreviewer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TVControllerTest {

    Tuner tuner = new FakeTuner();

    @Test
    @DisplayName("숫자버튼: 리모컨 ‘1’을 누르고 ‘확인’ 을 누르면 1번 채널로 변경된다")
    void shouldSetChannelWhenPressedOneDigitAndOkButton() {
        TVController tv = new TVController(tuner);

        tv.pushButton(remoteKey.KEY_1);
        tv.pushButton(remoteKey.KEY_OK);
        assertEquals(1, tuner.getCurrentCH());
    }

    @Test
    @DisplayName("숫자버튼: 리모컨 ‘1’을 누르고 ’2’ 를 누르면 12번 채널로 변경된다.")
    void shouldSetChannelWhenPressedTwoDigit() {
        TVController tv = new TVController(tuner);

        tv.pushButton(remoteKey.KEY_1);
        tv.pushButton(remoteKey.KEY_2);
        assertEquals(12, tuner.getCurrentCH());
    }

    @ParameterizedTest
    @DisplayName("숫자버튼: 숫자를 연속적으로 누를 경우")
    @MethodSource()
    void shouldSetChannelWhenPressedMoreThanTwoDigit(List<remoteKey> list, int expectedSetCH) {
        TVController tv = new TVController(tuner);

        for(remoteKey key : list) {
            tv.pushButton(key);
        }
        assertEquals(expectedSetCH, tuner.getCurrentCH());
    }
    static Stream<Arguments> shouldSetChannelWhenPressedMoreThanTwoDigit() {
        return Stream.of(
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_2, remoteKey.KEY_3, remoteKey.KEY_4), 34),
                arguments(Arrays.asList(remoteKey.KEY_4, remoteKey.KEY_5, remoteKey.KEY_6), 45),
                arguments(Arrays.asList(remoteKey.KEY_4, remoteKey.KEY_5, remoteKey.KEY_6, remoteKey.KEY_OK), 6),
                arguments(Arrays.asList(remoteKey.KEY_4, remoteKey.KEY_5, remoteKey.KEY_6, remoteKey.KEY_6), 66),
                arguments(Arrays.asList(remoteKey.KEY_4, remoteKey.KEY_5, remoteKey.KEY_6, remoteKey.KEY_CHFAV), 45),
                arguments(Arrays.asList(remoteKey.KEY_6, remoteKey.KEY_CHFAV, remoteKey.KEY_1, remoteKey.KEY_OK), 1),
                arguments(Arrays.asList(remoteKey.KEY_6, remoteKey.KEY_OK, remoteKey.KEY_OK, remoteKey.KEY_OK), 6),
                arguments(Arrays.asList(remoteKey.KEY_4, remoteKey.KEY_5, remoteKey.KEY_6, remoteKey.KEY_PRECH), 45)
        );
    }


    @Test
    @DisplayName("숫자버튼: ‘0’,’7’ 를 누를 경우 7번 채널로 변경된다.")
    void shouldSetChannelWhenPressedTwoDigitException() {
        TVController tv = new TVController(tuner);

        tv.pushButton(remoteKey.KEY_0);
        tv.pushButton(remoteKey.KEY_7);
        assertEquals(7, tuner.getCurrentCH());
    }

    @ParameterizedTest
    @DisplayName("이전채널: 이전 채널을 누르면 이전에 시청하던 채널로 변경된다")
    @MethodSource()
    void shouldSetPreChannelWhenPressedPRECHKey(List<remoteKey> list, int expectedSetCH) {
        TVController tv = new TVController(tuner);

        for(remoteKey key : list) {
            tv.pushButton(key);
        }
        assertEquals(expectedSetCH, tuner.getCurrentCH());
    }
    static Stream<Arguments> shouldSetPreChannelWhenPressedPRECHKey() {
        return Stream.of(
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_2, remoteKey.KEY_OK,
                                        remoteKey.KEY_PRECH), 1),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_2, remoteKey.KEY_OK,
                                        remoteKey.KEY_PRECH, remoteKey.KEY_PRECH), 2),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_2, remoteKey.KEY_OK,
                                        remoteKey.KEY_PRECH, remoteKey.KEY_PRECH, remoteKey.KEY_PRECH), 1),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_PRECH), 1),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_PRECH, remoteKey.KEY_PRECH), 1),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_4, remoteKey.KEY_5,
                                        remoteKey.KEY_6, remoteKey.KEY_CHFAV,
                                        remoteKey.KEY_PRECH), 1)
        );
    }

    @Test
    @DisplayName("선호채널: 선호 채널 목록에 추가/삭제")
    void shouldSetFavoriteChannelWhenPressedCHFAVKey1() {
        TVController tv = new TVController(tuner);

        tv.pushButton(remoteKey.KEY_1);
        tv.pushButton(remoteKey.KEY_OK);
        tv.pushButton(remoteKey.KEY_CHFAV);

        tv.pushButton(remoteKey.KEY_3);
        tv.pushButton(remoteKey.KEY_OK);
        tv.pushButton(remoteKey.KEY_CHFAV);

        tv.pushButton(remoteKey.KEY_1);
        tv.pushButton(remoteKey.KEY_OK);
        tv.pushButton(remoteKey.KEY_CHFAV);

        assertEquals(3, tv.getFavoriteChList().get(0));
    }

    @ParameterizedTest
    @DisplayName("선호채널: 선호 채널 목록에 추가/삭제 size check")
    @MethodSource()
    void shouldSetFavoriteChannelWhenPressedCHFAVKey2(List<remoteKey> list, int expectedFavoriteListSize) {
        TVController tv = new TVController(tuner);

        for(remoteKey key : list) {
            tv.pushButton(key);
        }
        assertEquals(expectedFavoriteListSize, tv.getFavoriteChList().size());
    }
    static Stream<Arguments> shouldSetFavoriteChannelWhenPressedCHFAVKey2() {
        return Stream.of(
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV), 1),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV, remoteKey.KEY_CHFAV), 0),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV,
                                        remoteKey.KEY_3, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV,
                                        remoteKey.KEY_5, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV), 3),
                arguments(Arrays.asList(remoteKey.KEY_1, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV,
                                        remoteKey.KEY_3, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV,
                                        remoteKey.KEY_5, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV,
                                        remoteKey.KEY_3, remoteKey.KEY_OK,
                                        remoteKey.KEY_CHFAV), 2)
        );
    }

    @ParameterizedTest
    @DisplayName("다음 선호채널 : 선호 채널 목록 내 이동")
    @MethodSource()
    void shouldSetChannelWhenPressedCHNEXTFAVKey(List<remoteKey> list, int expectedSetCH) {
        TVController tv = new TVController(tuner);

        Vector<Integer> favoriteChList = new Vector<>(Arrays.asList(1,4,12,56));
        tv.setFavoriteChList(favoriteChList);

        for(remoteKey key : list) {
            tv.pushButton(key);
        }
        assertEquals(expectedSetCH, tuner.getCurrentCH());
    }
    static Stream<Arguments> shouldSetChannelWhenPressedCHNEXTFAVKey() {
        return Stream.of(
                arguments(Arrays.asList(remoteKey.KEY_6, remoteKey.KEY_OK, remoteKey.KEY_CHNEXTFAV), 12),
                arguments(Arrays.asList(remoteKey.KEY_5, remoteKey.KEY_6, remoteKey.KEY_CHNEXTFAV), 1)
        );
    }

    @ParameterizedTest
    @DisplayName("다음 선호채널 : 선호 채널 목록 없는 상태에서 다음선호채널 누르면 채널 변경되지 않음")
    @MethodSource()
    void shouldStayChannelEmptyFavoriteCaseWhenPressedCHNEXTFAVKey(List<remoteKey> list, int expectedSetCH) {
        TVController tv = new TVController(tuner);

        Vector<Integer> favoriteChList = new Vector<>();
        tv.setFavoriteChList(favoriteChList);

        for(remoteKey key : list) {
            tv.pushButton(key);
        }
        assertEquals(expectedSetCH, tuner.getCurrentCH());
    }
    static Stream<Arguments> shouldStayChannelEmptyFavoriteCaseWhenPressedCHNEXTFAVKey() {
        return Stream.of(
                arguments(Arrays.asList(remoteKey.KEY_6, remoteKey.KEY_OK, remoteKey.KEY_CHNEXTFAV), 6),
                arguments(Arrays.asList(remoteKey.KEY_5, remoteKey.KEY_6, remoteKey.KEY_CHNEXTFAV), 56)
        );
    }

    @Test
    @DisplayName("채널검색 : 시청 가능한 채널 검색")
    void shouldSaveChannelDataWhenPressedCHSEARCHKey() {
        TVController tv = new TVController(tuner);

        tv.pushButton(remoteKey.KEY_CHSEARCH);

        assertTrue(false == tv.getAvailableCHList().isEmpty());

        FakeTuner fakeTuner = new FakeTuner();
        if(tv.getAvailableCHList().size() != fakeTuner.getAvailableCHData().size()) {
            fail();
        }
        for(int i=0; i<tv.getAvailableCHList().size(); i++) {
            if(tv.getAvailableCHList().get(i) != fakeTuner.getAvailableCHData().get(i)) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("업/다운 버튼 동작 : 저장된 채널 검색 결과가 없는 경우")
    void shouldUpDownChannelWithNoSavedAvailableChList() {
        TVController tv = new TVController(tuner);
        tv.setAvailableChList(new Vector<>());

        tuner.setCH("5");
        tv.pushButton(remoteKey.KEY_CHUP);
        assertEquals(6, tuner.getCurrentCH());

        tuner.setCH("5");
        tv.pushButton(remoteKey.KEY_CHDOWN);
        assertEquals(4, tuner.getCurrentCH());

        tuner.setCH("0");
        tv.pushButton(remoteKey.KEY_CHDOWN);
        assertEquals(99, tuner.getCurrentCH());

        tuner.setCH("99");
        tv.pushButton(remoteKey.KEY_CHUP);
        assertEquals(0, tuner.getCurrentCH());
    }

    @Test
    @DisplayName("업/다운 버튼 동작 : 저장된 채널 검색 결과가 있는 경우")
    void shouldUpDownChannelWhitExistSavedAvailableChList() {
        TVController tv = new TVController(tuner);
        tv.setAvailableChList(new Vector<>(Arrays.asList(10,20,30,40,50)));

        tuner.setCH("5");
        tv.pushButton(remoteKey.KEY_CHUP);
        assertEquals(10, tuner.getCurrentCH());

        tv.pushButton(remoteKey.KEY_CHDOWN);
        assertEquals(50, tuner.getCurrentCH());

        tv.pushButton(remoteKey.KEY_CHUP);
        assertEquals(10, tuner.getCurrentCH());

        tv.pushButton(remoteKey.KEY_CHUP);
        assertEquals(20, tuner.getCurrentCH());


    }
}

