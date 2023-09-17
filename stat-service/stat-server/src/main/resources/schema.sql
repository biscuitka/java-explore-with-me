DROP TABLE IF EXISTS endpoints_hits;

CREATE TABLE IF NOT EXISTS endpoints_hits
(
    hit_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    hit_app       VARCHAR,
    hit_uri       VARCHAR,
    hit_ip        VARCHAR,
    hit_timestamp TIMESTAMP WITHOUT TIME ZONE
);