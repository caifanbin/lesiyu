package com.binge.lesiyu.bean;

public class UserText {

    private long id;
    private long userid;
    private String text;
    private String username;
    private String time;
    private String title;
    private String preface;

    @Override
    public String toString() {
        return "UserText{" +
                "id=" + id +
                ", userid=" + userid +
                ", text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", preface='" + preface + '\'' +
                '}';
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
