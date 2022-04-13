create table if not exists table_user
(
    id       varchar      not null primary key,
    password varchar(255) not null,
    username varchar(255) not null,
    profile_id varchar(255) not null
);