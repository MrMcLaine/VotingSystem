DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('James', 'james21@gmail.com', 'james'),
       ('David', 'david_D@gmail.com', 'david');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100002),
       ('USER', 100003);

INSERT INTO meals (restaurant_id, description, price, date)
VALUES (100013,'Marine Soil (-20 M)', 30, now()),
    (100013,'Low Andes Mountain (1800 M)', 44, now()),
    (100013,'Extreme Stem (2875 M)', 26, now()),
    (100014,'gazpacho with "snow"', 28, now()),
    (100014,'Jewels of ‘White Pearl’ oysters with Corsican mint and Vodka-tonic gels', 77, now()),
    (100014,'Salted Pollack from Yeu Island with pine salt and fermented cabbage', 34, now()),
    (100015, 'Daisy Flower', 33, now()),
    (100015, 'Love to Eat Avocado Toast', 68, now()),
    (100015, 'Shining star', 49, now());

INSERT INTO restaurants (name, menu)
VALUES ('Central, Lima', 'empty menu'),
       ('Bon-Bon, Brussels', 'empty menu'),
       ('Atelier Crenn, San Francisco', 'empty menu');

INSERT INTO votes(user_id, date, restaurant_id)
VALUES (100000, now(), 100013),
       (100001, now(), 100013),
       (100002, now(), 100014),
       (100003, now(), 100015);


