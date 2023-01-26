package com.example.health_tracker;

public class Doctor {
    private User[] patients;

    public void addPatient(User patient) {
        patients[patients.length] = patient;
    }
}
