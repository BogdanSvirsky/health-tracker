package com.example.health_tracker;

public class User {
    private enum GOALS {
        STEPS, WATER, CALORIES
    }
    private int[] goalsValues = new int[GOALS.values().length];

    public void setGoalValue(GOALS goal, int goalValue) {
        goalsValues[goal.ordinal()] = goalValue;
    }
}
