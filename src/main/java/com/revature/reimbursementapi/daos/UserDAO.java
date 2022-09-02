package com.revature.reimbursementapi.daos;

import com.revature.reimbursementapi.models.User;

import java.io.IOException;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    @Override
    public void save(User obj) throws IOException {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
