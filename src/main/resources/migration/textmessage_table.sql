create table if not exists text_messages
(
    text varchar,
    FOREIGN KEY (sender_id) REFERENCES profile (id)
)
    inherits (abstract_messages);