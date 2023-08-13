package com.example.food_app_design.modal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user.tb")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String passWord;

    public User(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
