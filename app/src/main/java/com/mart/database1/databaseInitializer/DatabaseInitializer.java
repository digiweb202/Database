package com.mart.database1.databaseInitializer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mart.database1.database.mydatabase;
import com.mart.database1.entities.User;

public class DatabaseInitializer {

    private static final String DATABASE_NAME = "Persondb";
    private static mydatabase instance;

    public static synchronized mydatabase getInstance(Context context) {
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static mydatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(
                        context.getApplicationContext(),
                        mydatabase.class,
                        DATABASE_NAME
                )
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // You can perform initialization tasks here when the database is created.
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                        // Handle destructive migration (e.g., drop and recreate tables) here if needed.
                    }
                })
                .build();
    }
}