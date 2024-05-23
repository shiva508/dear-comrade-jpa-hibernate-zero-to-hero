package com.comrade.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "topicEntityManagerFactory",
        transactionManagerRef = "topicPlatformTransactionManager",
        basePackages = "com.comrade.repository.h2"
)
public class TopicDataBaseConfig {

    @Primary
    @Bean(name = "topicDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties topicDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "topicDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource topicDataSource(@Qualifier("topicDataSourceProperties") DataSourceProperties topicDataSourceProperties){
        return topicDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "topicEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean topicEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("topicDataSource") DataSource topicDataSource){
        Map<String, String> primaryJpaProperties = new HashMap<>();
        //primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        return entityManagerFactoryBuilder.dataSource(topicDataSource)
                                          .packages("com.comrade.entity.h2")
                                          .properties(primaryJpaProperties)
                                          .build();
    }

    @Bean(name = "topicPlatformTransactionManager")
    @ConfigurationProperties("spring.jpa")
    @Primary
    public PlatformTransactionManager topicPlatformTransactionManager(@Qualifier("topicEntityManagerFactory") EntityManagerFactory topicEntityManagerFactory){
        return new JpaTransactionManager(topicEntityManagerFactory);
    }

}
