package com.mart.database1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mart.database1.database.mydatabase;
import com.mart.database1.databaseInitializer.DatabaseInitializer;
import com.mart.database1.entities.User;
import com.mart.database1.model.PersonViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button Btn_submit;
    private Button Btn_show;
    private Button Btn_update;
    private Button Btn_delete;
    mydatabase Mydatabase;
    PersonViewModel personViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.edt_username);
        editTextPassword = findViewById(R.id.edt_password);
        Btn_submit = findViewById(R.id.btn_submit);
        Btn_show = findViewById(R.id.btn_get);
        Btn_update = findViewById(R.id.btn_update);
        Btn_delete = findViewById(R.id.btn_delete);



        // In your MainActivity or any other class
        Mydatabase = DatabaseInitializer.getInstance(getApplicationContext());

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
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        Btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace with the actual user ID you want to update
                int userIdToObserve = 1;

                // Observe changes to the single user LiveData
                personViewModel.getPersonById(userIdToObserve).observe(MainActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User userToUpdate) {
                        if (userToUpdate != null) {
                            // Perform the update
                            userToUpdate.setName("Updated Name");
                            userToUpdate.setAge("Updated Age");
                            personViewModel.update(userToUpdate);
                        }
                    }
                });
            }
        });

        Btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personViewModel = new ViewModelProvider(MainActivity.this).get(PersonViewModel.class);

                personViewModel.getAllPersons().observe(MainActivity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        // Find the user with ID 1
                        User userWithId1 = null;
                        for (User user : users) {
                            if (user.getId() == 2) {
                                userWithId1 = user;
                                break;
                            }
                        }

                        // Display the information for the user with ID 1
                        if (userWithId1 != null) {
                            StringBuilder data = new StringBuilder();
                           String Name =  userWithId1.getName();
                           String Password = userWithId1.getAge();
                           Toast.makeText(MainActivity.this,Name,Toast.LENGTH_SHORT).show();
                           Toast.makeText(MainActivity.this,Password,Toast.LENGTH_SHORT).show();

                            data.append("ID: ").append(userWithId1.getId()).append(", Name: ")
                                    .append(userWithId1.getName()).append(", Age: ")
                                    .append(userWithId1.getAge()).append("\n");

                            Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
        Btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with the actual user ID you want to delete
                int userIdToDelete = 1;

                // Perform the delete operation in a background thread
                new DeletePersonTask().execute(userIdToDelete);
            }
        });
    }
    // AsyncTask to delete a person in the background
    private class DeletePersonTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... userIds) {
            Mydatabase.getPersonDAO().deletePersonById(userIds[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Update UI or perform post-execution logic if needed
            // You can refresh the UI or show a message after deletion
        }
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
