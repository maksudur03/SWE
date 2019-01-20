package com.example.hp.swe;

public class Notice {
    String title,username,user_id,picture,notice_text,profilepic;
    public Notice(){

    }

    public Notice(String title,String username,String user_id,String picture,String notice_text,String profilepic){
        this.title = title;
        this.username = username;
        this.user_id = user_id;
        this.picture = picture;
        this.notice_text = notice_text;
        this.profilepic = profilepic;
    }

    public void setNotice_text(String notice_text) {
        this.notice_text = notice_text;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotice_text() {
        return notice_text;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }
}
