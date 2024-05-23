package com.comrade.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "com.comrade.repository.postgres",
        entityManagerFactoryRef = "topicTypeEntityManagerFactory",
        transactionManagerRef = "topicTypePlatformTransactionManager"
)
public class TopicTypeDataBaseConfig {

    @Bean(name = "topicTypeDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.topic-type.datasource")
    public DataSourceProperties topicTypeDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "topicTypeDataSource")
    @ConfigurationProperties(prefix = "spring.topic-type.datasource")
    public DataSource topicTypeDataSource(@Qualifier("topicTypeDataSourceProperties") DataSourceProperties topicTypeDataSourceProperties ){
        return topicTypeDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "topicTypeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean topicTypeEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                                @Qualifier("topicTypeDataSource") DataSource topicTypeDataSource){
        Map<String, String> primaryJpaProperties = new HashMap<>();
        //primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        return entityManagerFactoryBuilder
                                        .dataSource(topicTypeDataSource)
                                        .packages("com.comrade.entity.postgres")
                                        .properties(primaryJpaProperties)
                                        .build();
    }

    @Bean(name = "topicTypePlatformTransactionManager")
    @ConfigurationProperties(prefix = "spring.jpa")
    public PlatformTransactionManager topicTypePlatformTransactionManager(@Qualifier("topicTypeEntityManagerFactory") EntityManagerFactory topicTypeEntityManagerFactory){
        return new JpaTransactionManager(topicTypeEntityManagerFactory);
    }

}
