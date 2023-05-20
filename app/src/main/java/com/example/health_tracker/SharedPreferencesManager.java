package com.example.health_tracker;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.security.Key;

public class SharedPreferencesManager {
    private final String
            STEPS_KEY = "COUNT_STEPS",
            STEPS_GOAL_KEY = "STEPS_GOAL",
            WATER_GOAL_KEY = "WATER_GOAL",
            CALORIES_GOAL_KEY = "CALORIES_GOAL",
            CALORIES_RECORDS_KEY = "CALORIES_RECORDS",
            WATER_RECORDS_KEY = "WATER_RECORDS",
            LOG_STATUS_KEY = "LOG_STATUS",
            PREVIOUS_STEPS_KEY = "PREVIOUS_STEPS";
    private SharedPreferences sharedPreferences;

    public enum KEYS {CALORIES, WATER, STEPS}

    private enum TYPES {GOAL, COUNT, RECORDS}

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(
                "HEALTH TRACKER PREFERENCES",
                MODE_PRIVATE
        );
    }

    public void saveCount(KEYS enumKey, int value) {
        String key = getKeyByEnum(enumKey, TYPES.COUNT);
        if (key != null)
            sharedPreferences.edit()
                    .putInt(key, value)
                    .apply();
    }

    public int getCount(KEYS enumKey) {
        String key = getKeyByEnum(enumKey, TYPES.COUNT);
        if (key != null)
            return sharedPreferences.getInt(key, 0);
        return 0;
    }

    public void setGoal(KEYS enumKey, final int value) {
        String key = getKeyByEnum(enumKey, TYPES.GOAL);
        if (key != null) {
            sharedPreferences.edit()
                    .putInt(key, value)
                    .apply();
        }
    }

    public int getGoal(KEYS enumKey) {
        String key = getKeyByEnum(enumKey, TYPES.GOAL);
        if (key != null) {
            return sharedPreferences.getInt(key, 0);
        }
        return 0;
    }

    public void saveRecords(KEYS enumKey, String records) {
        String key = getKeyByEnum(enumKey, TYPES.RECORDS);
        if (key != null) {
            sharedPreferences.edit()
                    .putString(key, records)
                    .apply();
        }
    }

    public String getRecords(KEYS enumKey) {
        String key = getKeyByEnum(enumKey, TYPES.RECORDS);
        if (key != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    private String getKeyByEnum(KEYS enumKey, TYPES enumType) {
        String key = null;
        switch (enumKey) {
            case STEPS:
                switch (enumType) {
                    case GOAL:
                        key = STEPS_GOAL_KEY;
                        break;
                    case COUNT:
                        key = STEPS_KEY;
                        break;
                }
                break;
            case WATER:
                switch (enumType) {
                    case GOAL:
                        key = WATER_GOAL_KEY;
                        break;
                    case RECORDS:
                        key = WATER_RECORDS_KEY;
                        break;
                }
                break;
            case CALORIES:
                switch (enumType) {
                    case GOAL:
                        key = CALORIES_GOAL_KEY;
                        break;
                    case RECORDS:
                        key = CALORIES_RECORDS_KEY;
                        break;
                }
                break;
        }
        return key;
    }

    public void setLogStatus(final boolean status) {
        sharedPreferences.edit()
                .putBoolean(LOG_STATUS_KEY, status)
                .apply();
    }

    public boolean getLogStatus() {
        return sharedPreferences.getBoolean(LOG_STATUS_KEY, false);
    }

    public void setPreviousSteps(final int value) {
        sharedPreferences.edit()
                .putInt(PREVIOUS_STEPS_KEY, value)
                .apply();
    }

    public int getPreviousSteps() {
        return sharedPreferences.getInt(PREVIOUS_STEPS_KEY, 0);
    }

    public void reset() {
        sharedPreferences.edit().putInt(STEPS_KEY, 0).apply();
        sharedPreferences.edit().putString(CALORIES_RECORDS_KEY, "").apply();
        sharedPreferences.edit().putString(WATER_RECORDS_KEY, "").apply();
        sharedPreferences.edit().putInt(PREVIOUS_STEPS_KEY, 0).apply();
        Log.d("SHARED PREFS", "RESET IS OVER ");
    }
}
