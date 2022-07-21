DELETE
FROM menu_dish;
DELETE
FROM vote;
DELETE
FROM menu;
DELETE
FROM dish;
DELETE
FROM restaurant;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

insert into DISH
values (0, 'California Suncup', '86');
insert into DISH
values (1, 'Uluhe', '64');
insert into DISH
values (2, 'Wild Sweetwilliam', '57');
insert into DISH
values (3, 'Smallhead Cat''s Ear', '82');
insert into DISH
values (4, 'West Indian Mahogany', '56');
insert into DISH
values (5, 'Plains Blackberry', '61');
insert into DISH
values (6, 'Narrow-petal Rein Orchid', '34');
insert into DISH
values (7, 'Oryctes', '63');
insert into DISH
values (8, 'Horsetail', '51');
insert into DISH
values (9, 'Goldback Fern', '18');

insert into restaurant
values (0, 'Rau LLC', '1 Stone Corner Junction');
insert into restaurant
values (1, 'Sporer-Parisian', '16 Forest Junction');
insert into restaurant
values (2, 'O''Kon and Sons', '4916 Kim Street');
insert into restaurant
values (3, 'Bechtelar Group', '3 Dorton Court');
insert into restaurant
values (4, 'Wolf Group', '96923 Northwestern Crossing');
insert into restaurant
values (5, 'Little-Mills', '604 Eliot Place');
insert into restaurant
values (6, 'Tillman-Murray', '5 Spaight Trail');
insert into restaurant
values (7, 'Dickinson LLC', '9 Evergreen Park');
insert into restaurant
values (8, 'Jacobson, Nader and Kuhn', '503 Melrose Street');
insert into restaurant
values (9, 'Brekke, Corkery and Nitzsche', '58 Bartillon Junction');

insert into menu(id, restaurant_id)
values (0, 0);
insert into menu(id, restaurant_id)
values (1, 1);
insert into menu(id, restaurant_id)
values (2, 2);
insert into menu(id, restaurant_id)
values (3, 3);
insert into menu(id, restaurant_id)
values (4, 4);
insert into menu(id, restaurant_id)
values (5, 5);
insert into menu(id, restaurant_id)
values (6, 6);
insert into menu(id, restaurant_id)
values (7, 7);
insert into menu(id, restaurant_id)
values (8, 8);
insert into menu(id, restaurant_id)
values (9, 9);
-- https://stackoverflow.com/questions/70069266/sql-inster-into-with-now-minus-one-day
insert into menu
values (10, DATEADD(DAY, -1, CURRENT_DATE), 0);
insert into menu
values (11, DATEADD(DAY, -1, CURRENT_DATE), 1);
insert into menu
values (12, DATEADD(DAY, -1, CURRENT_DATE), 2);
insert into menu
values (13, DATEADD(DAY, -1, CURRENT_DATE), 3);
insert into menu
values (14, DATEADD(DAY, -1, CURRENT_DATE), 4);
insert into menu
values (15, DATEADD(DAY, -1, CURRENT_DATE), 5);
insert into menu
values (16, DATEADD(DAY, -1, CURRENT_DATE), 6);
insert into menu
values (17, DATEADD(DAY, -1, CURRENT_DATE), 7);
insert into menu
values (18, DATEADD(DAY, -1, CURRENT_DATE), 8);
insert into menu
values (19, DATEADD(DAY, -1, CURRENT_DATE), 9);

insert into menu_dish
values (0, 2);
insert into menu_dish
values (0, 1);
insert into menu_dish
values (1, 1);
insert into menu_dish
values (1, 2);
insert into menu_dish
values (1, 3);
insert into menu_dish
values (1, 4);
insert into menu_dish
values (2, 5);
insert into menu_dish
values (2, 6);
insert into menu_dish
values (2, 9);
insert into menu_dish
values (3, 1);
insert into menu_dish
values (3, 2);
insert into menu_dish
values (3, 3);
insert into menu_dish
values (4, 3);
insert into menu_dish
values (4, 4);
insert into menu_dish
values (4, 5);
insert into menu_dish
values (4, 8);
insert into menu_dish
values (5, 1);
insert into menu_dish
values (5, 2);
insert into menu_dish
values (5, 3);
insert into menu_dish
values (5, 9);
insert into menu_dish
values (6, 8);
insert into menu_dish
values (6, 7);
insert into menu_dish
values (6, 3);
insert into menu_dish
values (7, 0);
insert into menu_dish
values (8, 5);
insert into menu_dish
values (9, 3);

insert into menu_dish
values (10, 2);
insert into menu_dish
values (10, 4);
insert into menu_dish
values (11, 1);
insert into menu_dish
values (11, 2);
insert into menu_dish
values (12, 9);
insert into menu_dish
values (12, 3);
insert into menu_dish
values (12, 8);
insert into menu_dish
values (13, 7);
insert into menu_dish
values (13, 6);
insert into menu_dish
values (14, 3);
insert into menu_dish
values (14, 1);
insert into menu_dish
values (15, 2);
insert into menu_dish
values (15, 3);
insert into menu_dish
values (15, 4);
insert into menu_dish
values (16, 3);
insert into menu_dish
values (16, 6);
insert into menu_dish
values (17, 4);
insert into menu_dish
values (18, 8);
insert into menu_dish
values (18, 2);
insert into menu_dish
values (19, 3);

INSERT INTO USERS (id, name, email, password)
VALUES (1, 'User', 'user@gmail.com', '{noop}password'),
       (2, 'Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

