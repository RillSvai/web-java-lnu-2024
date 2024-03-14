CREATE TABLE smartphones (
     id BIGSERIAL PRIMARY KEY,
     brand VARCHAR(255),
     model UNIQUE VARCHAR(255),
     os VARCHAR(255),
     screen_size DOUBLE PRECISION,
     resolution VARCHAR(255),
     cpu_model VARCHAR(255),
     ram INTEGER,
     storage INTEGER,
     battery_capacity INTEGER,
     camera_resolution VARCHAR(255),
     nfc_support BOOLEAN
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
    email         VARCHAR(32)        NOT NULL,
    info          TEXT
);