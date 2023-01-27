package com.example.health_tracker;

import java.util.ArrayList;

public class User {
    public enum GOALS {
        STEPS, WATER, CALORIES
    }
    private ArrayList<Integer> goalsValues = new ArrayList<>(GOALS.values().length);

    public User() {
        this.goalsValues.set(1, 2200);
    }

    public void setGoalValue(GOALS goal, int goalValue) {
        goalsValues.set(goal.ordinal(), goalValue);
    }

    public int getGoalValue(GOALS goal){
        return goalsValues.get(goal.ordinal());
    }
}
