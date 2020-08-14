package com.muradismayilov.easy_english.model;

import java.util.Comparator;

public class User {

    private final String id;
    private final String username;
    private final String score;

    public User(String id, String username, String score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getScore() {
        return score;
    }

    public static final Comparator<User> comparator = (o1, o2) -> - Integer.valueOf(o1.score).compareTo(Integer.valueOf(o2.score));
}
