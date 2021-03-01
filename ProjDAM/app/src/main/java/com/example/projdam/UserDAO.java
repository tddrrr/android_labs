package com.example.projdam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
    @Delete
    void deleteUser(User user);
    @Update
    void updateUser(User user);
    @Query("SELECT * FROM users")
    List<User> getAllUsers();
    @Query("SELECT * FROM users WHERE first_name='Birliba'")
    List<User> getAllBirliba();
    @Query("SELECT * FROM users WHERE first_name=:name")
    List<User> getAllByName(String name);
}
