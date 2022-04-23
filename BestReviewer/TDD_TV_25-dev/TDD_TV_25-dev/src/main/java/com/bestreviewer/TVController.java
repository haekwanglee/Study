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

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class TVController {
    private Tuner tuner;
    private String processingCH;
    private String previousCH;
    private Vector<Integer> favoriteCHList;
    private Vector<Integer> availableCHList;

    public TVController(Tuner tuner) {
        this.tuner = tuner;
        processingCH = "";
        previousCH = "";
        favoriteCHList = new Vector<>();
        availableCHList = new Vector<>();
    }

    private static Map<remoteKey, Runnable> mapKeyFunc = new HashMap<>();
    {
        mapKeyFunc.put(remoteKey.KEY_0, ()->processPushNumber(remoteKey.KEY_0));
        mapKeyFunc.put(remoteKey.KEY_1, ()->processPushNumber(remoteKey.KEY_1));
        mapKeyFunc.put(remoteKey.KEY_2, ()->processPushNumber(remoteKey.KEY_2));
        mapKeyFunc.put(remoteKey.KEY_3, ()->processPushNumber(remoteKey.KEY_3));
        mapKeyFunc.put(remoteKey.KEY_4, ()->processPushNumber(remoteKey.KEY_4));
        mapKeyFunc.put(remoteKey.KEY_5, ()->processPushNumber(remoteKey.KEY_5));
        mapKeyFunc.put(remoteKey.KEY_6, ()->processPushNumber(remoteKey.KEY_6));
        mapKeyFunc.put(remoteKey.KEY_7, ()->processPushNumber(remoteKey.KEY_7));
        mapKeyFunc.put(remoteKey.KEY_8, ()->processPushNumber(remoteKey.KEY_8));
        mapKeyFunc.put(remoteKey.KEY_9, ()->processPushNumber(remoteKey.KEY_9));
        mapKeyFunc.put(remoteKey.KEY_OK, ()->setTunerCh());
        mapKeyFunc.put(remoteKey.KEY_CHUP, ()->processChannelUP());
        mapKeyFunc.put(remoteKey.KEY_CHDOWN, ()->processChannelDOWN());
        mapKeyFunc.put(remoteKey.KEY_CHFAV, ()->processFavoriteCh());
        mapKeyFunc.put(remoteKey.KEY_CHNEXTFAV, ()->processNextFavoriteCh());
        mapKeyFunc.put(remoteKey.KEY_CHSEARCH, ()->processSearchChannel());
        mapKeyFunc.put(remoteKey.KEY_PRECH, ()->processPreCh());
    }

    public void pushButton(remoteKey key) {
        mapKeyFunc.get(key).run();
    }

    public Vector<Integer> getAvailableCHList() {
        return availableCHList;
    }

    public void setAvailableChList(Vector<Integer> availableCHList) {
        this.availableCHList = availableCHList;
    }

    public Vector<Integer> getFavoriteChList() {
        return favoriteCHList;
    }

    public void setFavoriteChList(Vector<Integer> favoriteCHList) {
        this.favoriteCHList = favoriteCHList;
    }

    private void setTunerCh() {
        if(processingCH.isEmpty()) {
            System.out.println("processingCH empty");
            return;
        }

        previousCH = Integer.toString(tuner.getCurrentCH());

        System.out.println("현재 설정하는 채널 : " + processingCH);
        tuner.setCH(processingCH);
        processingCH = "";
    }

    private void processPushNumber(remoteKey key) {
        processingCH += key.toString();
        if(processingCH.length()>=2) {
            setTunerCh();
            processingCH = "";
        }
    }

    private void processPreCh() {
        processingCH = "";
        if(previousCH.isEmpty()) {
            return;
        }
        if(previousCH.equals("-1")) {
            return;
        }
        processingCH = previousCH;
        setTunerCh();
    }

    private void processFavoriteCh() {
        processingCH = "";
        int currentCH = tuner.getCurrentCH();
        if(favoriteCHList.contains(currentCH)) {
            favoriteCHList.remove((Object)currentCH);
            return;
        }
        favoriteCHList.add(currentCH);
    }

    private void processNextFavoriteCh() {
        processingCH = "";
        int currentCH = tuner.getCurrentCH();
        for(Integer ch : favoriteCHList) {
            if(currentCH < ch) {
                processingCH = Integer.toString(ch);
                setTunerCh();
                break;
            }
            if(favoriteCHList.size() >0 && ch == favoriteCHList.lastElement()) {
                processingCH = Integer.toString(favoriteCHList.get(0));
                setTunerCh();
                break;
            }
        }
    }

    private void processSearchChannel() {
        processingCH = "0";
        tuner.setCH(processingCH);
        availableCHList.clear();

        String preSeeked = "";
        String seeking = "";
        while(true) {
            seeking = tuner.seekCH();
            if(preSeeked.equals(seeking)) {
                break;
            }
            preSeeked = seeking;
            availableCHList.add(Integer.parseInt(seeking));
        }
        processingCH = "";
    }

    private void processChannelUP() {
        if(availableCHList.isEmpty()) {
            processChannelUPWithNotAvailableChList();
        }
        else {
            processChannelUPWithExistAvailableChList();
        }
        processingCH = "";
    }

    private void processChannelUPWithNotAvailableChList() {
        int currentCH = tuner.getCurrentCH();
        int targetCH = (currentCH == 99) ? 0 : currentCH+1;
        processingCH = Integer.toString(targetCH);
        setTunerCh();
    }

    private void processChannelUPWithExistAvailableChList() {
        int currentCH = tuner.getCurrentCH();
        for(Integer ch : availableCHList) {
            if(currentCH < ch) {
                processingCH = Integer.toString(ch);
                break;
            }
            if(availableCHList.size() >0 && ch == availableCHList.lastElement()) {
                processingCH = Integer.toString(availableCHList.firstElement());
                break;
            }
        }
        setTunerCh();
    }

    private void processChannelDOWN() {
        if(availableCHList.isEmpty()) {
            processChannelDOWNWithNotAvailableChList();
        }
        else {
            processChannelDOWNWithExistAvailableChList();
        }
        setTunerCh();
        processingCH = "";
    }

    private void processChannelDOWNWithNotAvailableChList() {
        int currentCH = tuner.getCurrentCH();
        int targetCH = (currentCH == 0) ? 99 : currentCH-1;
        processingCH = Integer.toString(targetCH);
        setTunerCh();
    }

    private void processChannelDOWNWithExistAvailableChList() {
        int currentCH = tuner.getCurrentCH();
        for(int i = availableCHList.size()-1; i>=0; i-- ) {
            int ch = availableCHList.get(i);
            if(currentCH > ch) {
                processingCH = Integer.toString(ch);
                break;
            }
            if(availableCHList.size() >0 && ch == availableCHList.firstElement()) {
                processingCH = Integer.toString(availableCHList.lastElement());
                break;
            }
        }
        setTunerCh();
    }
}
