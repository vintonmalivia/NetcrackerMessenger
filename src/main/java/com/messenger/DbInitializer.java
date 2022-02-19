package com.messenger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.sql.DataSource;
import java.util.*;

public class DbInitializer {

    public static void initializeTables(DataSource dataSource) {
        List<String> tableScripts = getTables();
        for (String script: tableScripts) {
            Resource resource = new ClassPathResource(script);
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(resource);
            resourceDatabasePopulator.execute(dataSource);
        }
    }

    private static List<String> getTables()
    {
        List<String> tables = new ArrayList<>();
        tables.add("migration/conversation_table.sql");
        tables.add("migration/profile_table.sql");
        tables.add("migration/conversation_members.sql");
        tables.add("migration/abstractmessage_table.sql");
        tables.add("migration/textmessage_table.sql");
        tables.add("migration/conversation_table.sql");
        return tables;
    }

    private void tableCheck() {

    }
}
