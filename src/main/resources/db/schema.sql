DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE smartphones (
                             id BIGSERIAL PRIMARY KEY,
                             brand VARCHAR(64) NOT NULL,
                             model VARCHAR(128) UNIQUE NOT NULL,
                             os VARCHAR(32) NOT NULL,
                             screen_size DOUBLE PRECISION NOT NULL,
                             resolution VARCHAR(32) NOT NULL,
                             cpu_model VARCHAR(64) NOT NULL,
                             ram INTEGER NOT NULL,
                             storage INTEGER NOT NULL,
                             battery_capacity INTEGER NOT NULL,
                             camera_resolution VARCHAR(32) NOT NULL,
                             nfc_support BOOLEAN NOT NULL
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(32) UNIQUE NOT NULL,
    password_hash VARCHAR(128)       NOT NULL,
    is_admin      BOOLEAN            NOT NULL,
    first_name    VARCHAR(64)        NOT NULL,
    middle_name   VARCHAR(64)        NOT NULL,
    last_name     VARCHAR(64)        NOT NULL,
    phone         VARCHAR(16)        NOT NULL,
    email         VARCHAR(32) UNIQUE NOT NULL,
    info          TEXT
);