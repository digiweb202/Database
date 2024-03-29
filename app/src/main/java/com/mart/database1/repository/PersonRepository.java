package com.mart.database1.repository;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.mart.database1.dao.userDao;
import com.mart.database1.database.mydatabase;
import com.mart.database1.entities.User;
import java.util.List;

public class PersonRepository {

    private userDao personDAO;
    private LiveData<List<User>> allPersons;

    public PersonRepository(mydatabase Mydatabase) {
        personDAO = Mydatabase.getPersonDAO();
        allPersons = personDAO.getAllPerson();
    }

    public LiveData<List<User>> getAllPersons() {
        return allPersons;
    }

    public void insert(User user) {
        new InsertPersonAsyncTask(personDAO).execute(user);
    }

    private static class InsertPersonAsyncTask extends AsyncTask<User, Void, Void> {
        private userDao personDAO;

        private InsertPersonAsyncTask(userDao personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            personDAO.addPerson(users[0]);
            return null;
        }
    }
    public void update(User user) {
        new UpdatePersonAsyncTask(personDAO).execute(user);
    }

    private static class UpdatePersonAsyncTask extends AsyncTask<User, Void, Void> {
        private userDao personDAO;

        private UpdatePersonAsyncTask(userDao personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            personDAO.updatePerson(users[0]);
            return null;
        }
    }
    public LiveData<User> getPersonById(int personId) {
        return personDAO.getPersonById(personId);
    }
}
