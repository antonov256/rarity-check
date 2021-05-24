package com.atriviss.raritycheck.config.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommonDataConfiguration {
    @Autowired
    private Environment env;

    private final String dialect = "spring.jpa.properties.hibernate.dialect";
    private final String ddlAuto = "spring.jpa.hibernate.ddl-auto";
    private final String showSql = "spring.jpa.hibernate.show-sql";

    @Bean(name = "jpaProperties")
    public Map<String, Object> jpaProperties() {
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(dialect, env.getProperty(dialect));
        jpaProperties.put(ddlAuto, env.getProperty(ddlAuto));
        jpaProperties.put(showSql, env.getProperty(showSql));

        return jpaProperties;
    }
}
