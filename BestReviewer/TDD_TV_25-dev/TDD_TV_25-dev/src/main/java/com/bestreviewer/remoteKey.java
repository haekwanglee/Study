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

enum remoteKey {
    KEY_1("1"),
    KEY_2("2"),
    KEY_3("3"),
    KEY_4("4"),
    KEY_5("5"),
    KEY_6("6"),
    KEY_7("7"),
    KEY_8("8"),
    KEY_9("9"),
    KEY_0("0"),
    KEY_PRECH("PRECH"),
    KEY_CHUP("CHUP"),
    KEY_CHDOWN("CHDOWN"),
    KEY_CHSEARCH("CHSEARCH"),
    KEY_CHFAV("CHFAV"),
    KEY_CHNEXTFAV("CHNEXTFAV"),
    KEY_OK("OK");

    final private String key;

    remoteKey(String key) {
        this.key = key;
    }

    public String toString() {
        return key;
    }
}
