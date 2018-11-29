package com.common.atmochat.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */

@Configuration
@EnableMongoRepositories(basePackages = "com.common.atmochat.data.repository")
public class MongoConfig {

//    @Bean
//    public Mongobee mongobee() throws Exception {
//        Mongobee runner = new Mongobee();
////        runner.setDbName("chat");         // host must be set if not set in URI
////        runner.setChangeLogsScanPackage(
////                "com.common.atmochat.db.changelogs"); // the package to be scanned for changesets
//        runner.setMongoTemplate(mongoTemplate());
//
//        return runner;
//    }

    @Bean
    public MongoClient mongo() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "chat");
    }

}
