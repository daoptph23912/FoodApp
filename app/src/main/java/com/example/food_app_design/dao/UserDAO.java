package com.example.food_app_design.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.food_app_design.modal.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("select * from `user.tb`")
    List<User>getList();
    @Insert
    void insert(User user);
}
