package com.example.health_tracker;

import com.example.health_tracker.models.User;

public class UserGetResponse {
    private final User user;

    public UserGetResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
