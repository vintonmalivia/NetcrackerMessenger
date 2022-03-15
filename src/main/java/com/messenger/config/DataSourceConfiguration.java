package com.messenger.config;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private static abstract class DatabaseData {
        private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
        private static final String URL = "jdbc:postgresql://localhost:5432/conversation_db";
        private static final String POSTGRES_USERNAME = "postgres";
        private static final String POSTGRES_PASSWORD = "postgres";
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(DatabaseData.DRIVER_CLASS_NAME);
        dataSourceBuilder.url(DatabaseData.URL);
        dataSourceBuilder.username(DatabaseData.POSTGRES_USERNAME);
        dataSourceBuilder.password(DatabaseData.POSTGRES_PASSWORD);
        return dataSourceBuilder.build();
    }
}
