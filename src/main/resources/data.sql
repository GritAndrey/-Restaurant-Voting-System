DELETE
FROM vote;
DELETE
FROM dish;
DELETE
FROM food;

DELETE
FROM restaurant;
DELETE
FROM user_roles;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant(name, address)
VALUES ('Rau LLC', '1 Stone Corner Junction'),
       ('Sporer-Parisian', '16 Forest Junction'),
       ('Kon and Sons', '4916 Kim Street'),
       ('Bechtelar Group', '3 Dorton Court');

INSERT INTO food(name)
VALUES ('California Suncup'),
       ('Uluhe'),
       ('Wild Sweetwilliam'),
       ('Smallhead Cat''s Ear'),
       ('West Indian Mahogany'),
       ('Plains Blackberry'),
       ('Narrow-petal Rein Orchid'),
       ('Oryctes'),
       ('Horsetail'),
       ('Goldback Fern');

INSERT INTO dish(restaurant_id, dish_date, dish_name_id, price)
VALUES (100000, now(), 100004, '12.3'),
       (100000, now(), 100005, '4.6'),
       (100000, now(), 100006, '3.5'),
       (100001, now(), 100007, '35.12'),
       (100001, now(), 100008, '12.5'),
       (100001, now(), 100009, '2.1'),
       (100002, now(), 100010, '0.3'),
       (100002, now(), 100011, '4.5'),
       (100002, now(), 100012, '6.4'),
       (100003, now(), 100013, '2.8'),
       (100003, now(), 100007, '22.5'),
       (100003, now(), 100008, '33.1');

-- https://stackoverflow.com/questions/70069266/sql-inster-into-with-now-minus-one-day
--for h2 db
--        (12, 0, dateadd('DAY', -1, current_date), 4, '4'),
--        (13, 0, dateadd('DAY', -1, current_date), 3, '3'),
--        (14, 0, dateadd('DAY', -1, current_date), 9, '9'),
--        (15, 1, dateadd('DAY', -1, current_date), 8, '8'),
--        (16, 1, dateadd('DAY', -1, current_date), 7, '7'),
--        (17, 1, dateadd('DAY', -1, current_date), 6, '6'),
--        (18, 2, dateadd('DAY', -1, current_date), 5, '5'),
--        (19, 2, dateadd('DAY', -1, current_date), 4, '4'),
--        (20, 2, dateadd('DAY', -1, current_date), 3, '3'),
--        (21, 3, dateadd('DAY', -1, current_date), 2, '2'),
--        (22, 3, dateadd('DAY', -1, current_date), 1, '1'),
--        (23, 3, dateadd('DAY', -1, current_date), 0, '12');

INSERT INTO users (id, name, email, password, enabled, registered)
VALUES (1, 'User', 'user@gmail.com', '{noop}password', TRUE, now()),
       (2, 'Admin', 'admin@gmail.com', '{noop}admin', TRUE, now());

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

