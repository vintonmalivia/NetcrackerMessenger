package com.messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Access;
import javax.sql.DataSource;

@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        //DB init
        /* TODO: Здесь нужно проинициализировать бд (создать таблички, ЕСЛИ ИХ НЕТ!)*/

        DataSourceConfiguration dataSourceConfiguration = new DataSourceConfiguration();
        DbInitializer.initializeTables(dataSourceConfiguration.getDataSource());

        SpringApplication.run(Main.class);
    }
}
