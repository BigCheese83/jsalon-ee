INSERT INTO discounts(id, name, value) VALUES (nextval('discounts_id_seq'), 'Серебрянная', 3);
INSERT INTO discounts(id, name, value) VALUES (nextval('discounts_id_seq'), 'Золотая', 5);
INSERT INTO discounts(id, name, value) VALUES (nextval('discounts_id_seq'), 'Сезонная', 10);
INSERT INTO discounts(id, name, value) VALUES (nextval('discounts_id_seq'), 'VIP', 20);

INSERT INTO posts(id, name) VALUES (nextval('posts_id_seq'), 'Мастер-универсал');
INSERT INTO posts(id, name) VALUES (nextval('posts_id_seq'), 'Парикмахер');
INSERT INTO posts(id, name) VALUES (nextval('posts_id_seq'), 'Массажист');
INSERT INTO posts(id, name) VALUES (nextval('posts_id_seq'), 'Косметолог');

INSERT INTO services(id, name, cost, duration, description) VALUES (nextval('services_id_seq'), 'Макияж', 700.5, 30, null);
INSERT INTO services(id, name, cost, duration, description) VALUES (nextval('services_id_seq'), 'Мелирование', 650, 20, null);
INSERT INTO services(id, name, cost, duration, description) VALUES (nextval('services_id_seq'), 'Восстановление волос', 2200.5, 30, null);
INSERT INTO services(id, name, cost, duration, description) VALUES (nextval('services_id_seq'), 'Фотоомоложение лица', 6400, 40, null);
INSERT INTO services(id, name, cost, duration, description) VALUES (nextval('services_id_seq'), 'Окрашивание волос', 5400, 55, 'краска Loreal');