package com.win.utility;


import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoAuth {

    private static final Logger logger = LoggerFactory.getLogger(MongoAuth.class);

    public static void main(String[] args) {
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB       db = mongo.getDB("test");

            if (db.authenticate("test", "test".toCharArray())) {
                logger.info("Authorization was successful!");
                DBCollection collection = db.getCollection("users");
            } else {
                logger.error("Unexpected error?");
            }
        } catch (UnknownHostException e) {
            logger.debug("(UnknownHostException) " + e.getMessage());
        } catch (MongoException e) {
            logger.debug("(MongoException) " + e.getMessage());
        }
    }

}
