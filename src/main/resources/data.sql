DELETE
FROM vote;
DELETE
FROM dish;
DELETE
FROM restaurant_dish;
DELETE
FROM restaurant;
DELETE
FROM user_roles;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant
VALUES (0, 'Rau LLC', '1 Stone Corner Junction'),
       (1, 'Sporer-Parisian', '16 Forest Junction'),
       (2, 'O''Kon and Sons', '4916 Kim Street'),
       (3, 'Bechtelar Group', '3 Dorton Court');

INSERT INTO dish(id, name)
VALUES (0, 'California Suncup'),
       (1, 'Uluhe'),
       (2, 'Wild Sweetwilliam'),
       (3, 'Smallhead Cat''s Ear'),
       (4, 'West Indian Mahogany'),
       (5, 'Plains Blackberry'),
       (6, 'Narrow-petal Rein Orchid'),
       (7, 'Oryctes'),
       (8, 'Horsetail'),
       (9, 'Goldback Fern');

INSERT INTO restaurant_dish(id, restaurant_id, dish_date, dish_name_id, price)
VALUES (0, 0, now(), 0, '86'),
       (1, 0, now(), 1, '64'),
       (2, 0, now(), 2, '57'),
       (3, 1, now(), 3, '86'),
       (4, 1, now(), 4, '86'),
       (5, 1, now(), 5, '57'),
       (6, 2, now(), 6, '86'),
       (7, 2, now(), 7, '86'),
       (8, 2, now(), 8, '57'),
       (9, 3, now(), 9, '86'),
       (10, 3, now(), 3, '86'),
       (11, 3, now(), 4, '57');
-- https://stackoverflow.com/questions/70069266/sql-inster-into-with-now-minus-one-day
--for h2 db
-- (12, 0, dateadd('DAY', -1, current_date), 4, '4'),
-- (13, 0, dateadd('DAY', -1, current_date), 3, '3'),
-- (14, 0, dateadd('DAY', -1, current_date), 9, '9'),
-- (15, 1, dateadd('DAY', -1, current_date), 8, '8'),
-- (16, 1, dateadd('DAY', -1, current_date), 7, '7'),
-- (17, 1, dateadd('DAY', -1, current_date), 6, '6'),
-- (18, 2, dateadd('DAY', -1, current_date), 5, '5'),
-- (19, 2, dateadd('DAY', -1, current_date), 4, '4'),
-- (20, 2, dateadd('DAY', -1, current_date), 3, '3'),
-- (21, 3, dateadd('DAY', -1, current_date), 2, '2'),
-- (22, 3, dateadd('DAY', -1, current_date), 1, '1'),
-- (23, 3, dateadd('DAY', -1, current_date), 0, '12');


INSERT INTO users (id, name, email, password)
VALUES (1, 'User', 'user@gmail.com', '{noop}password'),
       (2, 'Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

