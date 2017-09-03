package com.win.utility.service;


import com.win.utility.model.User;
import com.win.utility.model.enumeration.UserType;


import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    List<User> getAllUsers(UserType type);

    User getUserByName(String name);

    void createUser(String name, String password);

    void updatePassword(String name, String oldPassword, String newPassword);

    void deleteUser(String name);

}
