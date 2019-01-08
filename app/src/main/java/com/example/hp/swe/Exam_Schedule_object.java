package com.example.hp.swe;

public class Exam_Schedule_object {
    String date,time,subject;

    public Exam_Schedule_object(){

    }

    public Exam_Schedule_object(String date, String time, String subject){
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public String getSubject() {
        return subject;
    }
    public String getDate() {
        return date;
    }
}
