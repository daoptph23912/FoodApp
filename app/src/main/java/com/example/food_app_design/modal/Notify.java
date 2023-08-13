package com.example.food_app_design.modal;

public class Notify {
    private  int id;
    private String title;
    private String times;
    private int userId;

    public Notify() {
    }

    public Notify(int id, String title, String times, int userId) {
        this.id = id;
        this.title = title;
        this.times = times;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Notify{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", times='" + times + '\'' +
                ", userId=" + userId +
                '}';
    }
}
