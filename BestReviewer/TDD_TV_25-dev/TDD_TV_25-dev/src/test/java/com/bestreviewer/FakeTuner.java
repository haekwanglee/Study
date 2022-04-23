package com.bestreviewer;

import java.util.Arrays;
import java.util.Vector;

public class FakeTuner implements Tuner {

    private String currentTuneCH;
    private Vector<Integer> availableCHData;

    public FakeTuner() {
        currentTuneCH = "";
        availableCHData = new Vector<>(Arrays.asList(10,20,30,40,50));
    }

    public Vector<Integer> getAvailableCHData() {
        return availableCHData;
    }

    @Override
    public String seekCH() {
        int currentTuneCHInt = Integer.parseInt(currentTuneCH);
        for(Integer ch : availableCHData) {
            if(currentTuneCHInt < ch) {
                currentTuneCH = Integer.toString(ch);
                break;
            }
        }
        return currentTuneCH;
    }

    @Override
    public void setCH(String ch) throws IllegalArgumentException {
        if(false == currentTuneCH.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("invalid ch value");
        }
        currentTuneCH = ch;
    }

    @Override
    public int getCurrentCH() {
        if(currentTuneCH.isEmpty()) {
            return -1;
        }
        if(false == currentTuneCH.chars().allMatch(Character::isDigit)) {
            return -1;
        }
        System.out.println("fake tuner 현재 채널 : " + currentTuneCH);
        return Integer.parseInt(currentTuneCH);
    }
}
