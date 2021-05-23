package com.atriviss.raritycheck.config.data;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:data_sources.properties"})
@EnableJpaRepositories(
        basePackages = "com.atriviss.raritycheck.repository.rc_users",
        entityManagerFactoryRef = "usersEntityManagerFactory",
        transactionManagerRef= "usersTransactionManager"
)
public class RcUsersPersistenceAutoConfiguration {
    @Autowired
    private Environment env;

    @Bean(name = "usersDataSourceProperties")
    @ConfigurationProperties("spring.datasource.users")
    public DataSourceProperties usersDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "usersDataSource")
    @ConfigurationProperties("spring.datasource.users.configuration")
    public HikariDataSource usersDataSource(DataSourceProperties usersDataSourceProperties) {
        HikariDataSource dataSource = usersDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        return dataSource;
    }

    @Bean(name = "usersEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean usersEntityManagerFactory(@Qualifier("usersDataSource") HikariDataSource usersDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(usersDataSource);
        em.setPackagesToScan("com.atriviss.raritycheck.dto_jpa.rc_users");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("spring.jpa.properties.hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("spring.jpa.hibernate.ddl-auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("spring.jpa.hibernate.show-sql", env.getProperty("spring.jpa.hibernate.show-sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "usersTransactionManager")
    public PlatformTransactionManager usersTransactionManager(final @Qualifier("usersEntityManagerFactory") LocalContainerEntityManagerFactoryBean usersEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(usersEntityManagerFactory.getObject()));
    }
}
