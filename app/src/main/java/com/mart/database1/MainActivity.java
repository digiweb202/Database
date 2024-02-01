package com.mart.database1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mart.database1.database.mydatabase;
import com.mart.database1.entities.User;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button Btn_submit;

    mydatabase Mydatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.edt_username);
        editTextPassword = findViewById(R.id.edt_password);
        Btn_submit = findViewById(R.id.btn_submit);

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        };
        Mydatabase = Room.databaseBuilder(getApplicationContext(), mydatabase.class, "Persondb").addCallback(myCallBack).build();

        Btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                User user1 = new User(username, password);

                // Perform database operation in a background thread
                new InsertPersonTask().execute(user1);
            }
        });
    }

    // AsyncTask to insert a person in the background
    private class InsertPersonTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            Mydatabase.getPersonDAO().addPerson(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Update UI or perform post-execution logic if needed
            editTextUsername.setText("");
            editTextPassword.setText("");
        }
    }
}
