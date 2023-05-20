package com.example.health_tracker.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("stepGoal")
    @Expose
    private Integer stepGoal;
    @SerializedName("waterGoal")
    @Expose
    private Integer waterGoal;
    @SerializedName("caloriesGoal")
    @Expose
    private Integer caloriesGoal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStepGoal() {
        return stepGoal;
    }

    public void setStepGoal(Integer stepGoal) {
        this.stepGoal = stepGoal;
    }

    public Integer getWaterGoal() {
        return waterGoal;
    }

    public void setWaterGoal(Integer waterGoal) {
        this.waterGoal = waterGoal;
    }

    public Integer getCaloriesGoal() {
        return caloriesGoal;
    }

    public void setCaloriesGoal(Integer caloriesGoal) {
        this.caloriesGoal = caloriesGoal;
    }

}