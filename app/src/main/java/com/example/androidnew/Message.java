package com.example.androidnew;

public class Message {
    String sender;
    String receiver;
    String content;
    long time;
    String isPhoto="false";
    String photopath;

    public Message(String sender, String receiver, String content, long time, String isPhoto, String photopath) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.time = time;
        this.isPhoto = isPhoto;
        this.photopath = photopath;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(String isPhoto) {
        this.isPhoto = isPhoto;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Message() {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
