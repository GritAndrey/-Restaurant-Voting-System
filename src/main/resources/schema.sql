SET MODE PostgreSQL;
DROP TABLE IF EXISTS menu_dish;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH  100000;
CREATE SEQUENCE user_seq START WITH 100000;
CREATE TABLE users
(
    id         BIGINT DEFAULT nextval('user_seq') PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role    VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id      INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    menu_date     DATE DEFAULT now() NOT NULL,
    restaurant_id INTEGER,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE SET NULL
);
CREATE UNIQUE INDEX menu_unique_restaurant_date_idx on menu (restaurant_id, menu_date);

CREATE TABLE dish
(
    id    INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    price NUMERIC(10)  NOT NULL
);



CREATE TABLE menu_dish
(
    menu_id INTEGER,
    dish_id INTEGER,
    PRIMARY KEY (dish_id, menu_id)
);

CREATE TABLE vote
(
    id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    vote_date     DATE DEFAULT CURRENT_DATE NOT NULL,
    vote_time     TIME DEFAULT CURRENT_TIME NOT NULL,
    user_id       BIGINT,
    restaurant_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE SET NULL,
    CONSTRAINT vote_unique_restaurant_user_date_idx UNIQUE (restaurant_id, user_id, vote_date)
);



