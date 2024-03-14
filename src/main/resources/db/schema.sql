CREATE TABLE smartphones (
     id BIGSERIAL PRIMARY KEY,
     brand VARCHAR(255),
     model VARCHAR(255),
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