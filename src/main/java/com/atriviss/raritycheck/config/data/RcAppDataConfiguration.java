package com.atriviss.raritycheck.config.data;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;
import java.util.Objects;

@Configuration
@PropertySource({"classpath:data_sources.properties"})
@EnableJpaRepositories(
        basePackages = "com.atriviss.raritycheck.repository.rc_app",
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager"
)
public class RcAppDataConfiguration {
    private final String packagesToScan = "com.atriviss.raritycheck.dto_jpa.rc_app";

    @Bean(name = "appDataSourceProperties")
    @ConfigurationProperties("spring.datasource.app")
    public DataSourceProperties appDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "rcAppDataSource")
    @ConfigurationProperties("spring.datasource.app.configuration")
    public HikariDataSource rcAppDataSource(DataSourceProperties appDataSourceProperties) {
        HikariDataSource dataSource = appDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        return dataSource;
    }

    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            @Qualifier("jpaProperties") Map<String, Object> jpaProperties,
            @Qualifier("rcAppDataSource") HikariDataSource rcAppDataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(packagesToScan);
        em.setJpaPropertyMap(jpaProperties);
        em.setDataSource(rcAppDataSource);
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean(name = "appTransactionManager")
    public PlatformTransactionManager appTransactionManager(@Qualifier("appEntityManagerFactory") LocalContainerEntityManagerFactoryBean appEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(appEntityManagerFactory.getObject()));
    }
}
