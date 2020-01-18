package com.project.multitenant.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Getter
@Setter
@Slf4j
@ConfigurationProperties(prefix = "multi.config.mongo")
@Configuration
public class MongoConfiguration {

    private List<MultiTenant> properties;

    @Getter
    @Setter
    static class MultiTenant {
        private String tenant;
        private MongoProperties properties;
        private String[] basePackages;
    }

    @PostConstruct
    private void init() {
        log.info("Properties: {}", properties);
    }
}
