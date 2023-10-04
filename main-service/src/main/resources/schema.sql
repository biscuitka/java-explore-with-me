DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS participation_requests CASCADE;
DROP TABLE IF EXISTS compilations CASCADE;
DROP TABLE IF EXISTS compilations_events CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    user_id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name  VARCHAR NOT NULL,
    user_email VARCHAR NOT NULL,
    CONSTRAINT unique_user_email UNIQUE (user_email)

);

CREATE TABLE IF NOT EXISTS categories
(
    category_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_name VARCHAR NOT NULL,

    CONSTRAINT unique_category_name UNIQUE (category_name)

);

CREATE TABLE IF NOT EXISTS locations
(
    location_id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    location_lat FLOAT NOT NULL,
    location_lon FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    event_id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    event_annotation         VARCHAR                     NOT NULL,
    event_title              VARCHAR                     NOT NULL,
    event_description        VARCHAR                     NOT NULL,

    event_category_id        BIGINT                      NOT NULL,
    event_initiator_id       BIGINT                      NOT NULL,
    event_location_id        BIGINT                      NOT NULL,

    event_created_on         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    event_published_on       TIMESTAMP WITHOUT TIME ZONE,
    event_date               TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    event_state              VARCHAR                     NOT NULL,
    event_participant_limit  INTEGER default 0,
    event_paid               BOOLEAN DEFAULT false,
    event_request_moderation BOOLEAN default false,

    CONSTRAINT fk_events_to_users FOREIGN KEY (event_initiator_id) REFERENCES users (user_id),
    CONSTRAINT fk_events_to_categories FOREIGN KEY (event_category_id) REFERENCES categories (category_id),
    CONSTRAINT fk_events_to_locations FOREIGN KEY (event_location_id) REFERENCES locations (location_id)
);

CREATE TABLE IF NOT EXISTS participation_requests
(
    request_id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    request_requester_id BIGINT                      NOT NULL,
    request_event_id     BIGINT                      NOT NULL,
    request_status       VARCHAR                     NOT NULL,
    request_created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT unique_requester_event UNIQUE (request_requester_id, request_event_id),
    CONSTRAINT fk_attendee_request_to_users FOREIGN KEY (request_requester_id) REFERENCES users (user_id),
    CONSTRAINT fk_attendee_request_to_events FOREIGN KEY (request_event_id) REFERENCES events (event_id)
);

CREATE TABLE IF NOT EXISTS compilations
(
    compilation_id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    compilation_title     VARCHAR NOT NULL,
    compilation_is_pinned BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS compilations_events
(
    compilation_id BIGINT,
    event_id       BIGINT,

    PRIMARY KEY (compilation_id, event_id),
    CONSTRAINT fk_compilations_events_to_compilations FOREIGN KEY (compilation_id) REFERENCES compilations (compilation_id),
    CONSTRAINT fk_compilations_events_to_events FOREIGN KEY (event_id) REFERENCES events (event_id)
);

CREATE TABLE IF NOT EXISTS comments
(
    comment_id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    comment_author_id     BIGINT NOT NULL,
    comment_event_id      BIGINT NOT NULL,
    comment_text          VARCHAR NOT NULL,
    comment_created_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT fk_comments_to_users FOREIGN KEY (comment_author_id) REFERENCES users (user_id),
    CONSTRAINT fk_comments_to_events FOREIGN KEY (comment_event_id) REFERENCES events (event_id)
);


