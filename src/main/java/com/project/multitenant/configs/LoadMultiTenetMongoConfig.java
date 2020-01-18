package com.project.multitenant.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class LoadMultiTenetMongoConfig {

    @Autowired
    private MongoConfiguration configuration;

    @Bean(name = "multiTenantMongoConfig")
    public Map<String, MongoOperations> getMultiTenantMongoConfig(MongoConfiguration mongoConfiguration) {

        Map<String, MongoOperations> multiTenantMongoConfig = new HashMap<>();
        List<MongoConfiguration.MultiTenant> multiTenantList = configuration.getProperties();
        for(MongoConfiguration.MultiTenant multiTenant : multiTenantList){
            String host = multiTenant.getProperties().getHost();
            Integer port = multiTenant.getProperties().getPort();
            String database = multiTenant.getProperties().getDatabase();

            String connection = "mongodb://"+host+":"+port;
            log.info("Host: {}", multiTenant.getProperties().getHost());
            log.info("Port: {}", multiTenant.getProperties().getPort());
            log.info("Database: {}", multiTenant.getProperties().getDatabase());

            MongoClient client = MongoClients.create(connection);
            MongoTemplate mongoTemplate = new MongoTemplate(client, database);
            multiTenantMongoConfig.put(multiTenant.getTenant(), mongoTemplate);
        }

        return multiTenantMongoConfig;
    }
}
