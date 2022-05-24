create table if not exists table_users_roles
(
    users_id varchar not null,
    roles_id varchar not null references table_roles,
    primary key (users_id, roles_id)
);