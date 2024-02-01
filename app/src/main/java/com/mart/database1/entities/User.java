package com.mart.database1.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userlogin")
public class User {

    @ColumnInfo(name = "person_id")
    @PrimaryKey(autoGenerate = true)
    long id;  // Change to long

    @ColumnInfo(name = "username")
    String name;

    @ColumnInfo(name = "password")
    String age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
