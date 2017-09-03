package com.win.utility.dao;

import com.win.utility.model.User;
import com.win.utility.model.enumeration.UserType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;


public class UserDao {

    private MongoTemplate template;

    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }

    public List<User> getAllUsers() {
        return template.findAll(User.class);
    }

    public List<User> getAllUserSort(UserType type){
        Query query = null;
        switch (type){
            case NAME:
                query = new Query().with(new Sort("_id", "-1"));
                break;
            default:
                query = new Query().with(new Sort("username", "-1"));
                break;
        }
        return template.find(query, User.class);
    }

    public User getUserByName(String name) {
        Query query = new Query(Criteria.where("username").is(name));
        return template.findOne(query, User.class);
    }

    public void createUser(String name, String password){
        User user = new User(name, password);
        template.save(user);
    }

    public void updatePassword(String name, String oldPassword, String newPassword){
        Query query = new Query(Criteria.where("username").is(name));
        template.updateFirst(query, Update.update(oldPassword, newPassword), User.class);
    }

    public void deleteUser(String name){
        Query query = new Query(Criteria.where("username").is(name));
        template.remove(query, User.class);
    }
}
