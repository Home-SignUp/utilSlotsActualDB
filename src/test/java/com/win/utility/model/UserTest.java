package com.win.utility.model;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@ContextConfiguration(classes = {SpringMongoConfiguration.class})
@ContextConfiguration("classpath:spring/applicationContext.xml")
//@DataSet(locations = "user.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
////@UsingDataSet(locations = {"/user.json"}, loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class UserTest {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Rule
//    public MongoDbRule mongoDbRule = new MongoDbRule().defaultSpringMongoDb("test");

    @Before
    public void init() {
    }

    @Test
    public void test0FindAll() {
        List<User> users
                = mongoTemplate.findAll(User.class);

        for (User user: users){
            System.out.println( user );
        }
    }

    @Test
    public void test1ByCreate() {
        int actual = mongoTemplate.findAll(User.class).size();
        int expect = actual + 1;

        User createUser = new User("username-1", "password-X");
        mongoTemplate.save(createUser);
        actual = mongoTemplate.findAll(User.class).size();

//        System.out.println( mongoTemplate.findAll(User.class).size() );
        assertEquals("Count By Create", expect, actual);

        User user = mongoTemplate.findOne(new Query(Criteria.where("username").is("username-1")), User.class);
        System.out.println(user);
//        assertEquals("username-1", user.getUsername());
        assertNotNull("NotNull By Create", user);
    }

    @Test
    public void test2ByUpdate() {
        int actual = mongoTemplate.findAll(User.class).size();
        int expect = actual;

        mongoTemplate.updateFirst(
                new Query(Criteria.where("username").is("username-1")),
                Update.update("password", "password-XXX"),
                User.class);

        actual = mongoTemplate.findAll(User.class).size();

        assertEquals("NotCount By Update", expect, actual);
//
        User user = mongoTemplate.findOne(new Query(Criteria.where("username").is("username-1")), User.class);
//        System.out.println(user);
        assertEquals("By Update", user.getPassword(), "password-XXX");
    }

    @Test
    public void test3ByDelete() {
        int actual = mongoTemplate.findAll(User.class).size();
        int expect = actual - 1;

        int count = mongoTemplate.remove(new Query(Criteria.where("username").is("username-1")), User.class).getN();

        actual = mongoTemplate.findAll(User.class).size();

//        System.out.println( mongoTemplate.findAll(User.class).size() );
        assertEquals("Count By Delete", expect, actual);

        User user = mongoTemplate.findOne(new Query(Criteria.where("username").is("username-1")), User.class);
        System.out.println(user);
        assertNull("Is Null By Delete", user);
    }
}
