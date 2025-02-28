INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Cliente 1');
INSERT INTO client(name) VALUES ('Cliente 2');
INSERT INTO client(name) VALUES ('Cliente 3');
INSERT INTO client(name) VALUES ('Cliente 4');

INSERT INTO prestamo(gamename, clientname, inidate, enddate) VALUES ('On Mars', 'Cliente 1', '01-01-2025', '01-01-2025');
INSERT INTO prestamo(gamename, clientname, inidate, enddate) VALUES ('Aventureros al tren', 'Cliente 2', '01-01-2025', '01-01-2025');
INSERT INTO prestamo(gamename, clientname, inidate, enddate) VALUES ('1920: Wall Street', 'Cliente 3', '01-01-2025', '01-01-2025');
INSERT INTO prestamo(gamename, clientname, inidate, enddate) VALUES ('Barrage', 'Cliente 4', '01-01-2025', '01-01-2025');
INSERT INTO prestamo(gamename, clientname, inidate, enddate) VALUES ('Los viajes de Marco Polo', 'Cliente 1', '01-01-2025', '01-01-2025');
INSERT INTO prestamo(gamename, clientname, inidate, enddate) VALUES ('Azul', 'Cliente 2', '01-01-2025', '01-01-2025');
