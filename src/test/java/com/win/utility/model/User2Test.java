//package com.win.utility.model;
//
//
//import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import com.win.utility.config.TestLocalMongoConfiguration;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.foursquare.fongo.Fongo;
//import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
//import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
//import com.mongodb.Mongo;
//
//import org.junit.FixMethodOrder;
//import org.junit.runners.MethodSorters;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestLocalMongoConfiguration.class)
////@ComponentScan(basePackageClasses = {TestLocalMongoConfiguration.class}, excludeFilters = @ComponentScan.Filter(pattern={"spring/applicationContext.xml"}))
//@UsingDataSet(locations = {"user.json"})
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class User2Test {
//
//    private static final Logger logger = LoggerFactory.getLogger(User2Test.class);
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Rule
//    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("user-db");
//
//
//    @Test
//    public void test1Size(){
//        logger.info( "size=" + mongoTemplate.findAll(User.class).size() );
//    }
//}
