package com.example.health_tracker.health_managers;

public class WaterManager {
    private int todayVolume = 0; // amount of water consumed today by milliliters
    private enum CUP_TYPE {
        SMALL_CUP, MEDIUM_CUP, BIG_CUP
    }

    public void setTodayVolume(CUP_TYPE cup) {
        switch (cup){
            case BIG_CUP: this.todayVolume += 400; break;
            case MEDIUM_CUP: this.todayVolume += 300; break;
            case SMALL_CUP: this.todayVolume += 200; break;
        }
    }

    public int getTodayVolume() {
        return todayVolume;
    }
}
