ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant(name, address)
VALUES ('Rau LLC', '1 Stone Corner Junction'),
       ('Sporer-Parisian', '16 Forest Junction'),
       ('Kon and Sons', '4916 Kim Street'),
       ('Bechtelar Group', '3 Dorton Court');

INSERT INTO menu_item(restaurant_id, dish_date, name, price)
VALUES (100000, now(), 'California Suncup', '12.3'),
       (100000, now(), 'Uluhe', '4.6'),
       (100000, now(), 'Wild Sweetwilliam', '3.5'),
       (100001, now(), 'Smallhead Cat''s Ear', '35.12'),
       (100001, now(), 'West Indian Mahogany', '12.5'),
       (100001, now(), 'Plains Blackberry', '2.1'),
       (100002, now(), 'Narrow-petal Rein Orchid', '0.3'),
       (100002, now(), 'Oryctes', '4.5'),
       (100002, now(), 'Horsetail', '6.4'),
       (100003, now(), 'Goldback Fern', '2.8'),
       (100003, now(), 'Smallhead Cat''s Ear', '22.5'),
       (100003, now(), 'West Indian Mahogany', '33.1');

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
VALUES (2, 100000, dateadd('DAY', -1, current_date), '11:01'),
       (1, 100000, now(), '12:00'),
       (2, 100001, now(), '10:00');

INSERT INTO menu_item(restaurant_id, dish_date, name, price)
VALUES (100003, dateadd('DAY', -2, current_date), 'California Suncup', '12.3'),
       (100003, dateadd('DAY', -2, current_date), 'Uluhe', '4.6'),
       (100003, dateadd('DAY', -2, current_date), 'Wild Sweetwilliam', '3.5'),
       (100002, dateadd('DAY', -2, current_date), 'Smallhead Cat''s Ear', '35.12'),
       (100002, dateadd('DAY', -2, current_date), 'West Indian Mahogany', '12.5'),
       (100002, dateadd('DAY', -2, current_date), 'Plains Blackberry', '2.1'),
       (100001, dateadd('DAY', -2, current_date), 'Narrow-petal Rein Orchid', '0.3'),
       (100001, dateadd('DAY', -2, current_date), 'Oryctes', '4.5'),
       (100001, dateadd('DAY', -2, current_date), 'Horsetail', '6.4'),
       (100000, dateadd('DAY', -2, current_date), 'Goldback Fern', '2.8'),
       (100000, dateadd('DAY', -2, current_date), 'Smallhead Cat''s Ear', '22.5'),
       (100000, dateadd('DAY', -2, current_date), 'West Indian Mahogany', '33.1'),

       (100000, dateadd('DAY', -1, current_date), 'California Suncup', '12.3'),
       (100001, dateadd('DAY', -1, current_date), 'Uluhe', '4.6'),
       (100002, dateadd('DAY', -1, current_date), 'Wild Sweetwilliam', '3.5'),
       (100003, dateadd('DAY', -1, current_date), 'Smallhead Cat''s Ear', '35.12'),
       (100000, dateadd('DAY', -1, current_date), 'West Indian Mahogany', '12.5'),
       (100001, dateadd('DAY', -1, current_date), 'Plains Blackberry', '2.1'),
       (100002, dateadd('DAY', -1, current_date), 'Narrow-petal Rein Orchid', '0.3'),
       (100003, dateadd('DAY', -1, current_date), 'Oryctes', '4.5'),
       (100000, dateadd('DAY', -1, current_date), 'Horsetail', '6.4'),
       (100001, dateadd('DAY', -1, current_date), 'Goldback Fern', '2.8'),
       (100002, dateadd('DAY', -1, current_date), 'Smallhead Cat''s Ear', '22.5'),
       (100003, dateadd('DAY', -1, current_date), 'West Indian Mahogany', '33.1');
