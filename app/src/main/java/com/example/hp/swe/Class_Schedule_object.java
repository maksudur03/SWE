package com.example.hp.swe;

public class Class_Schedule_object {
    String time,subject;

    public Class_Schedule_object(){

    }

    public Class_Schedule_object(String time, String subject){
        this.time = time;
        this.subject = subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
