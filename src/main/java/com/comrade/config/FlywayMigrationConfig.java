package com.comrade.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayMigrationConfig {

    //@Bean
    public Flyway flyway(DataSource dataSource){
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .table("db_migration_tracking")
                .load();
    }
}
