package com.mart.database1.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mart.database1.dao.userDao;
import com.mart.database1.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class mydatabase extends RoomDatabase {
//    Now creating An abstract class for our database
    // that will be  implementation related in database entities related work
    public abstract userDao getPersonDAO();



}
