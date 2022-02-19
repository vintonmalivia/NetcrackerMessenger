create table if not exists conversation_members
(
    conv_ID varchar,
    FOREIGN KEY (conv_ID) REFERENCES conversations (id),

    prof_ID varchar,
    FOREIGN KEY (prof_ID) REFERENCES profile (id)
);