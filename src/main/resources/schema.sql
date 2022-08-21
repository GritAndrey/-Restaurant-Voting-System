DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER   DEFAULT nextval('global_seq') PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT uk_user_roles UNIQUE (user_id, role)
);

CREATE TABLE restaurant
(
    id      INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE food
(
    id   INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
CREATE INDEX food_name_idx ON food (name);

CREATE TABLE dish
(
    id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    dish_date     DATE    DEFAULT now() NOT NULL,
    restaurant_id INTEGER REFERENCES restaurant (id) ON DELETE CASCADE,
    dish_name_id  INTEGER REFERENCES food (id) ON DELETE CASCADE,
    price         NUMERIC(20, 3)        NOT NULL
);
CREATE UNIQUE INDEX dish_restaurant_date_idx ON dish (restaurant_id, dish_date, dish_name_id);


CREATE TABLE vote
(
    id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    vote_date     DATE    DEFAULT current_date NOT NULL,
    vote_time     TIME    DEFAULT current_time NOT NULL,
    user_id       BIGINT,
    restaurant_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE SET NULL,
    CONSTRAINT vote_unique_restaurant_user_date_idx UNIQUE (restaurant_id, user_id, vote_date)
);

