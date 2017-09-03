//package com.win.utility.config;
//
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@EnableMongoRepositories
//public class SpringMongoConfiguration {
//
//    @Autowired
//    @Bean(name="mongoTemplate")
//    public MongoTemplate mongoTemplate() throws Exception {
////        return new MongoTemplate(new MongoClient("localhost", 27017), "test");
//        return new MongoTemplate(new MongoClient(new MongoClientURI("mongodb://test:test@localhost")), "test");
//    }
//}
