DROP TABLE user_roles IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE menu_item IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id       INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name     VARCHAR(255)         NOT NULL,
    email    VARCHAR(255)         NOT NULL,
    password VARCHAR(255)         NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX restaurant_unique_name_idx
    ON restaurant (name);

CREATE TABLE menu_item
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    date_menu_item     date DEFAULT NOW(),
    restaurant_id INTEGER      NOT NULL,
    description   VARCHAR(255) NOT NULL,
    price         INTEGER,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id       INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    voting_date   DATE    NOT NULL,
    foreign key (user_id) REFERENCES users (id) ON DELETE CASCADE,
    foreign key (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX vote_idx
    ON vote (user_id, voting_date);



