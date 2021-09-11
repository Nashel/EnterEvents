package com.example.entereventsproject.models;

/**
 * Created by tarda on 27/04/17.
 */

public class User {
    public String email;
    public String name;

    public User(){}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
