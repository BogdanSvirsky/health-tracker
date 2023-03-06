package com.example.health_tracker.health_managers;

public class WaterManager {
    private int todayVolume = 0; // amount of water consumed today by milliliters
    public enum CUP_TYPE {
        SMALL_CUP, SMALL_MEDIUM_CUP, MEDIUM_CUP, BIG_MEDIUM_CUP, BIG_CUP // TODO: create normal names)
    }

    public void setCurrentCup(CUP_TYPE currentCup) {
        this.currentCup = currentCup;
    }

    private CUP_TYPE currentCup = CUP_TYPE.SMALL_CUP;

    public void addCup(CUP_TYPE cup) {
        switch (cup){
            case BIG_CUP: this.todayVolume += 600; break;
            case BIG_MEDIUM_CUP: this.todayVolume += 500; break;
            case MEDIUM_CUP: this.todayVolume += 400; break;
            case SMALL_MEDIUM_CUP: this.todayVolume += 300; break;
            case SMALL_CUP: this.todayVolume += 200; break;
        }
    }

    public int getTodayVolume() {
        return todayVolume;
    }
}
