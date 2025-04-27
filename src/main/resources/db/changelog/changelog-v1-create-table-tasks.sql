--liquibase formatted sql

--changeset dev:1
CREATE TABLE IF NOT EXISTS task(
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    is_completed BOOLEAN DEFAULT FALSE
);
--rollback DROP TABLE task;