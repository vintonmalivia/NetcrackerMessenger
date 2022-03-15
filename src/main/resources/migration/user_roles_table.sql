create table if not exists table_user_roles
(
    users_id varchar not null,
    roles_id varchar not null references table_role,
    primary key (users_id, roles_id)
);