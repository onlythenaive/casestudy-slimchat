package com.onlythenaive.casestudy.slimchat.service.core.configuration.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

/**
 * Configuration of persistence-related components.
 *
 * @author Ilia Gubarev
 */
@Configuration
public class PersistenceConfiguration {

    @Autowired
    private MongoProperties mongodb;

    /**
     * Creates a new MongoDB client.
     *
     * @return MongoDB client.
     */
    @Bean
    public MongoClient mongoClientBean() {
        return new MongoClient(this.mongodb.getHost(), this.mongodb.getPort());
    }

    /**
     * Creates a new MongoDB template to perform various queries.
     *
     * @param mongoClient a MongoDB client to be used
     * @return MongoDB template.
     */
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplateBean(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, this.mongodb.getDatabase());
    }
}
