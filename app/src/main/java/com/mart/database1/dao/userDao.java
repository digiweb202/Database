package com.mart.database1.dao;

import androidx.lifecycle.LiveData;
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
    void addPerson(User user);

    @Update
    void updatePerson(User user);

    @Delete
    void deletePerson(User user);

    @Query("SELECT * FROM userlogin")
    LiveData<List<User>> getAllPerson(); // Change the return type to LiveData

    @Query("SELECT * FROM userlogin WHERE person_id = :person_id")
    User getPerson(int person_id);
    @Query("SELECT * FROM userlogin WHERE person_id = :personId")
    LiveData<User> getPersonById(int personId);

    @Query("DELETE FROM userlogin WHERE person_id = :personId")
    void deletePersonById(int personId);

}


