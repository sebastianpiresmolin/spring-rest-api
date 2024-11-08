INSERT INTO category (name, symbol, description)
VALUES ('Food service', '🍔', 'Restaurant, food truck or another great food spot that you want to share.'),
       ('Vista', '👀', 'A nice view or breathtaking spot that you want to share.'),
       ('Gas station', '⛽', 'Gas station or service point for vehicles.' ),
       ('Obstacle', '🚧', 'Indicating that there is a obstacle on the path.');

INSERT INTO location (name, category_id, user_id, is_private, created, last_modified, description, coordinates)
    VALUES ('Bree Town Sausages', 1, 'thestrider1337', 0, NOW(), NOW(), 'Very nice pig sausages right outside the Prancing Pony Inn.',  ST_GeomFromText('POINT(100 100)')),
           ('Weather Top Ruins', 2, 'nazgul1', 0, NOW(), NOW(), 'Great spot to fry some bacon and tomatoes!', ST_GeomFromText('POINT(50 -152)')),
           ('Rivendell Gas and Bread', 3, 'elr0nd', 1, NOW(), NOW(), 'Stop for supplies and rest.', ST_GeomFromText('POINT(12 44)')),
           ('Bride of Kazad-Dun', 4, 'somegoblin523', 0, NOW(), NOW(), 'Apparantly the bridge has been cut in half, think its going to be out for order for a while', ST_GeomFromText('POINT(-42 1)'));
