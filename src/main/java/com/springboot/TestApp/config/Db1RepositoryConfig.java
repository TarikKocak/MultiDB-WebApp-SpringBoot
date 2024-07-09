package com.springboot.TestApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.springboot.TestApp.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class Db1RepositoryConfig {
}
