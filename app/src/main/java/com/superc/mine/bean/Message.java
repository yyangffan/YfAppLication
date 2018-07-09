package com.superc.mine.bean;

/**
 * Created by user on 2018/6/5.
 */

public class Message {
    private String name;
    private String time;
    private String content;
    private int icon_id;

    public Message(String name, String time, String content, int icon_id) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.icon_id = icon_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }
}
