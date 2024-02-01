package com.mart.database1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mart.database1.entities.User;

import java.util.List;
@Dao
public interface userDao {
@Insert
    public void addPerson(User user);
@Update
    public void updatePerson(User user);
@Delete
    public void deletePerson(User user);

    @Query("select * from user")
    public List<User> getAllPerson();
    @Query("select * from user where person_id ==:person_id")
    public User getPerson(int person_id);


}
