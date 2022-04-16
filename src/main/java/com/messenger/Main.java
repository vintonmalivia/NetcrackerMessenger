package com.messenger;

import com.messenger.config.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        DataSourceConfiguration dataSourceConfiguration = new DataSourceConfiguration();
        DbInitializer.initializeTables(dataSourceConfiguration.getDataSource());
        SpringApplication.run(Main.class);
    }
}
