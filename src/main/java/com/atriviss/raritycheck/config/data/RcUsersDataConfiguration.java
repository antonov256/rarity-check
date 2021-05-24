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
        basePackages = "com.atriviss.raritycheck.repository.rc_users",
        entityManagerFactoryRef = "usersEntityManagerFactory",
        transactionManagerRef = "usersTransactionManager"
)
public class RcUsersDataConfiguration {
    private final String packagesToScan = "com.atriviss.raritycheck.dto_jpa.rc_users";

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
    public LocalContainerEntityManagerFactoryBean usersEntityManagerFactory(
            @Qualifier("jpaProperties") Map<String, Object> jpaProperties,
            @Qualifier("usersDataSource") HikariDataSource usersDataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(packagesToScan);
        em.setJpaPropertyMap(jpaProperties);
        em.setDataSource(usersDataSource);
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean(name = "usersTransactionManager")
    public PlatformTransactionManager usersTransactionManager(@Qualifier("usersEntityManagerFactory") LocalContainerEntityManagerFactoryBean usersEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(usersEntityManagerFactory.getObject()));
    }
}
