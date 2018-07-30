package com.tw.springboot.helloworld.entity;


public class Todo {

    private int id;
    private String text;
    private boolean completed;
    public static int IdNum = 1;

    public Todo(String text, boolean completed) {
        this.id = IdNum;
        this.text = text;
        this.completed = completed;
        ++IdNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
