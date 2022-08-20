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



INSERT INTO users (id, name, email, password, enabled, registered)
VALUES (1, 'User', 'user@gmail.com', '{noop}password', TRUE, now()),
       (3, 'noVoteUser', 'novote@gmail.com', '{noop}password', TRUE, now()),
       (2, 'Admin', 'admin@gmail.com', '{noop}admin', TRUE, now());

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 3),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO vote (user_id, restaurant_id, vote_date, vote_time)
VALUES (2, 100000, now() - INTERVAL '1 day', '11:01'),
       (1, 100000, now(), '12:00'),
       (2, 100001, now(), '10:00');
-- https://stackoverflow.com/questions/70069266/sql-inster-into-with-now-minus-one-day
--for h2 db
-- INSERT INTO vote (user_id, restaurant_id, vote_date, vote_time)
-- VALUES (2, 100000, dateadd('DAY', -1, current_date), '11:01'),
--        (1, 100000, now(), '12:00'),
--        (2, 100001, now(), '10:00');

