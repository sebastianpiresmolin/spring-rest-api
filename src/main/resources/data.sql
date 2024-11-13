SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE category;
TRUNCATE TABLE location;

SET FOREIGN_KEY_CHECKS = 1;

/* I am aware that its not a good solution to fiddle with key checks. But i needed truncate to work
   while im developing */

INSERT INTO category (name, symbol, description)
VALUES ('Food service', '🍔', 'Restaurant, food truck or another great food spot that you want to share.'),
       ('Vista', '👀', 'A nice view or breathtaking spot that you want to share.'),
       ('Gas station', '⛽', 'Gas station or service point for vehicles.' ),
       ('Obstacle', '🚧', 'Indicating that there is a obstacle on the path.');

INSERT INTO location (name, category_id, user_id, is_private, created, last_modified, description, longitude, latitude, deleted)
    VALUES ('Bree Town Sausages', 1, 'thestrider1337', 0, NOW(), NOW(), 'Very nice pig sausages right outside the Prancing Pony Inn.', 90, 50, 0),
           ('Weather Top Ruins', 2, 'nazgul1', 0, NOW(), NOW(), 'Great spot to fry some bacon and tomatoes!', 50, 50, 0),
           ('Rivendell Gas and Bread', 3, 'elr0nd', 1, NOW(), NOW(), 'Stop for supplies and rest.', -25, 36, 0),
           ('Bridge of Kazad-Dun', 4, 'somegoblin523', 0, NOW(), NOW(), 'Apparantly the bridge has been cut in half, think its going to be out of order for a while', 1, 57, 0);
