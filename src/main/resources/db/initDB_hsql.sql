DROP TABLE dish_menu IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE restaurant IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    date_time     TIMESTAMP DEFAULT now() NOT NULL,
    restaurant_id INTEGER,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE SET NULL
);
CREATE UNIQUE INDEX menu_unique_restaurant_date_idx on menu (restaurant_id, date_time);

CREATE TABLE dish
(
    id    INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    price NUMERIC(10)  NOT NULL
);

CREATE TABLE dish_menu
(
    dish_id INTEGER,
    menu_id INTEGER,
    PRIMARY KEY (dish_id, menu_id)
);

CREATE TABLE vote
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    vote_date     DATE DEFAULT CURRENT_DATE NOT NULL,
    vote_time     TIME DEFAULT CURRENT_TIME NOT NULL,
    user_id       INTEGER,
    restaurant_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE SET NULL,
    CONSTRAINT vote_unique_restaurant_user_date_idx UNIQUE (restaurant_id, user_id, vote_date)
);



