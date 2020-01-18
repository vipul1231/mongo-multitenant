package com.project.multitenant.repository;

import com.project.multitenant.model.Country;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;


@Repository
public class CountryRepository {

    @Resource(name = "multiTenantMongoConfig")
    private Map<String, MongoOperations> multiTenantMongoConfig;

    public boolean saveCountry(Country country){
        MongoTemplate mongoTemplate = (MongoTemplate) multiTenantMongoConfig.get(country.getCountry());
        mongoTemplate.save(country);
        return true;
    }

}
