create table if not exists table_roles
(
    id   varchar not null primary key,
    name varchar(255)
);

INSERT INTO table_roles VALUES ('0ada0e40-c34b-48ce-9ddc-be67eec99eed', 'ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO table_roles VALUES ('ca7c0960-9cbf-11ec-b909-0242ac120002', 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;