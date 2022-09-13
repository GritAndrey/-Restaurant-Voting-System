INSERT INTO restaurant(name, address)
VALUES ('Rau LLC', '1 Stone Corner Junction'),
       ('Sporer-Parisian', '16 Forest Junction'),
       ('Kon and Sons', '4916 Kim Street'),
       ('Bechtelar Group', '3 Dorton Court');

INSERT INTO users (name, email, password, enabled, registered)
VALUES ('User', 'user@gmail.com', '{noop}password', TRUE, now()),
       ('Admin', 'admin@gmail.com', '{noop}admin', TRUE, now()),
       ('noVoteUser', 'novote@gmail.com', '{noop}password', TRUE, now());

INSERT INTO menu_item(restaurant_id, dish_date, name, price)
VALUES (1, now(), 'California Suncup', '12.3'),
       (1, now(), 'Uluhe', '4.6'),
       (1, now(), 'Wild Sweetwilliam', '3.5'),
       (2, now(), 'Smallhead Cat''s Ear', '35.12'),
       (2, now(), 'West Indian Mahogany', '12.5'),
       (2, now(), 'Plains Blackberry', '2.1'),
       (3, now(), 'Narrow-petal Rein Orchid', '0.3'),
       (3, now(), 'Oryctes', '4.5'),
       (3, now(), 'Horsetail', '6.4'),
       (4, now(), 'Goldback Fern', '2.8'),
       (4, now(), 'Smallhead Cat''s Ear', '22.5'),
       (4, now(), 'West Indian Mahogany', '33.1');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('USER', 3),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO vote (user_id, restaurant_id, vote_date, vote_time)
VALUES (2, 1, dateadd('DAY', -1, current_date), '11:01'),
       (1, 1, now(), '12:00'),
       (2, 2, now(), '10:00');

INSERT INTO menu_item(restaurant_id, dish_date, name, price)
VALUES (4, dateadd('DAY', -2, current_date), 'California Suncup', '12.3'),
       (4, dateadd('DAY', -2, current_date), 'Uluhe', '4.6'),
       (4, dateadd('DAY', -2, current_date), 'Wild Sweetwilliam', '3.5'),
       (3, dateadd('DAY', -2, current_date), 'Smallhead Cat''s Ear', '35.12'),
       (3, dateadd('DAY', -2, current_date), 'West Indian Mahogany', '12.5'),
       (3, dateadd('DAY', -2, current_date), 'Plains Blackberry', '2.1'),
       (2, dateadd('DAY', -2, current_date), 'Narrow-petal Rein Orchid', '0.3'),
       (2, dateadd('DAY', -2, current_date), 'Oryctes', '4.5'),
       (2, dateadd('DAY', -2, current_date), 'Horsetail', '6.4'),
       (1, dateadd('DAY', -2, current_date), 'Goldback Fern', '2.8'),
       (1, dateadd('DAY', -2, current_date), 'Smallhead Cat''s Ear', '22.5'),
       (1, dateadd('DAY', -2, current_date), 'West Indian Mahogany', '33.1'),

       (1, dateadd('DAY', -1, current_date), 'California Suncup', '12.3'),
       (2, dateadd('DAY', -1, current_date), 'Uluhe', '4.6'),
       (3, dateadd('DAY', -1, current_date), 'Wild Sweetwilliam', '3.5'),
       (4, dateadd('DAY', -1, current_date), 'Smallhead Cat''s Ear', '35.12'),
       (1, dateadd('DAY', -1, current_date), 'West Indian Mahogany', '12.5'),
       (2, dateadd('DAY', -1, current_date), 'Plains Blackberry', '2.1'),
       (3, dateadd('DAY', -1, current_date), 'Narrow-petal Rein Orchid', '0.3'),
       (4, dateadd('DAY', -1, current_date), 'Oryctes', '4.5'),
       (1, dateadd('DAY', -1, current_date), 'Horsetail', '6.4'),
       (2, dateadd('DAY', -1, current_date), 'Goldback Fern', '2.8'),
       (3, dateadd('DAY', -1, current_date), 'Smallhead Cat''s Ear', '22.5'),
       (4, dateadd('DAY', -1, current_date), 'West Indian Mahogany', '33.1');
