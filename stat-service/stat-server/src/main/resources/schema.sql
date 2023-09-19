DROP TABLE IF EXISTS endpoints_hits;
DROP TABLE IF EXISTS applications;

CREATE TABLE IF NOT EXISTS applications
(
    app_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    app_name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS endpoints_hits
(
    hit_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    app_id        BIGINT,
    hit_uri       VARCHAR,
    hit_ip        VARCHAR,
    hit_timestamp TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_endpoints_hits_to_applications FOREIGN KEY (app_id) REFERENCES applications (app_id) ON DELETE CASCADE
);