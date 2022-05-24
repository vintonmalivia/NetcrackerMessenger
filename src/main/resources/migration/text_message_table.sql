create table if not exists text_messages
(
    text_value varchar,
    FOREIGN KEY (sender_id) REFERENCES profiles (id)
)
    inherits (abstract_messages);