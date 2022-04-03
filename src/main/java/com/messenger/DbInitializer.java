package com.messenger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DbInitializer {

    private static abstract class ScriptsName{
        private static final String CONVERSATION_TABLE = "migration/conversation_table.sql";
        private static final String PROFILE_TABLE = "migration/profile_table.sql";
        private static final String CONVERSATION_MEMBERS_TABLE = "migration/conversation_members.sql";
        private static final String ABSTRACT_MESSAGE_TABLE = "migration/abstractmessage_table.sql";
        private static final String TEXT_MESSAGE_TABLE = "migration/textmessage_table.sql";
        private static final String ROLE_TABLE = "migration/role_table.sql";
        private static final String USER_TABLE = "migration/user_table.sql";
        private static final String USER_ROLES_TABLE = "migration/user_roles_table.sql";
    }

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
        tables.add(ScriptsName.CONVERSATION_TABLE);
        tables.add(ScriptsName.PROFILE_TABLE);
        tables.add(ScriptsName.CONVERSATION_MEMBERS_TABLE);
        tables.add(ScriptsName.ABSTRACT_MESSAGE_TABLE);
        tables.add(ScriptsName.TEXT_MESSAGE_TABLE);
        tables.add(ScriptsName.ROLE_TABLE);
        tables.add(ScriptsName.USER_TABLE);
        tables.add(ScriptsName.USER_ROLES_TABLE);
        return tables;
    }
}
