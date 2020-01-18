package com.project.multitenant.repository;

import com.project.multitenant.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class CountryRepository {

    @Autowired
    private Map<String, MongoTemplate> multiTenantMongo;

    public boolean saveCountry(Country country){
        MongoTemplate mongoTemplate = multiTenantMongo.get(country.getCountry());
        mongoTemplate.save(country);
        return true;
    }

}
