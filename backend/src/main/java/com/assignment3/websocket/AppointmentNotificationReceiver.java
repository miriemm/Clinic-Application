package com.assignment3.websocket;

public class AppointmentNotificationReceiver {

    private String content;

    public AppointmentNotificationReceiver() {
    }

    public AppointmentNotificationReceiver(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
