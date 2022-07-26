package com.example.androidnew;

import java.lang.reflect.Constructor;

public class User {
    private String username;
    private String email;
    private String profilepicture;
    private long time;
    private String id;
    private String lastMessageTo;
    private String getLastMessageFrom;
    private int rank;
    public User() {

    }

    public User(String username, String email,
                String profilepicture, long time, String id, String lastMessageTo, String getLastMessageFrom, int rank) {
        this.username = username;
        this.email = email;
        this.profilepicture = profilepicture;
        this.time = time;
        this.id = id;
        this.lastMessageTo = lastMessageTo;
        this.getLastMessageFrom = getLastMessageFrom;
        this.rank = rank;
    }
//
//    public User(String username, String email, String profilepicture, long time, String id,
//                String lastMessageTo, String getLastMessageFrom) {
//        this.username = username;
//        this.email = email;
//        this.profilepicture = profilepicture;
//        this.time = time;
//        this.id = id;
//        this.lastMessageTo = lastMessageTo;
//        this.getLastMessageFrom = getLastMessageFrom;
//    }
//
//    public User(String username, String email, String profilepicture, long time, String id) {
//        this.username = username;
//        this.email = email;
//        this.profilepicture = profilepicture;
//        this.time = time;
//        this.id = id;
//    }
//
//    public User(String username, String email, String profilepicture, long time) {
//        this.username = username;
//        this.email = email;
//        this.profilepicture = profilepicture;
//        this.time = time;
//    }

//    public User(String username, String email, String profilepicture) {
//        this.username = username;
//        this.email = email;
//        this.profilepicture = profilepicture;
//    }

    public String getLastMessageTo() {
        return lastMessageTo;
    }

    public void setLastMessageTo(String lastMessageTo) {
        this.lastMessageTo = lastMessageTo;
    }

    public String getGetLastMessageFrom() {
        return getLastMessageFrom;
    }

    public void setGetLastMessageFrom(String getLastMessageFrom) {
        this.getLastMessageFrom = getLastMessageFrom;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    //    public User(String username, String profilepicture) {
//        this.username = username;
//        this.profilepicture = profilepicture;
//    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }
}
