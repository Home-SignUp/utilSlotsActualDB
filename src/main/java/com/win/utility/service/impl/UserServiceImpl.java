package com.win.utility.service.impl;

import com.win.utility.dao.UserDao;
import com.win.utility.model.User;
import com.win.utility.model.enumeration.UserType;
import com.win.utility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao dao;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<User> getAllUsers(UserType type){
        return dao.getAllUserSort(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void createUser(String name, String password){
        dao.createUser(name, password);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePassword(String name, String oldPassword, String newPassword){
        dao.updatePassword(name, oldPassword, newPassword);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUser(String name){
        dao.deleteUser(name);
    }

}
