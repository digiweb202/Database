package com.mart.database1.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.mart.database1.database.mydatabase;
import com.mart.database1.databaseInitializer.DatabaseInitializer;
import com.mart.database1.entities.User;
import com.mart.database1.repository.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository repository;
    private LiveData<List<User>> allPersons;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        mydatabase Mydatabase = Room.databaseBuilder(
                application,
                mydatabase.class,
                DatabaseInitializer.DATABASE_NAME // Use the constant here
        ).build();
        repository = new PersonRepository(Mydatabase);
        allPersons = repository.getAllPersons();
    }

    public LiveData<List<User>> getAllPersons() {
        return allPersons;
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public LiveData<User> getPersonById(int personId) {
        return repository.getPersonById(personId);
    }

}
