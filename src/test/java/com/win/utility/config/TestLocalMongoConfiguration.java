package com.win.utility.config;

import com.foursquare.fongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories
@ComponentScan(basePackageClasses = { MongoTemplate.class })
public class TestLocalMongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "user-db";
    }

    @Override
    public Mongo mongo() {
        return new Fongo("user-test").getMongo();
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.win.utility";
    }

//    @Autowired
//    Mongo newMongo;
//
//    @Bean
//    public Mongo newMongo()
//            throws UnknownHostException
//    {
//        return new Mongo("localhost", 27017);
//    }

}
