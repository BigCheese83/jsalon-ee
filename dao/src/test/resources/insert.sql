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

INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country)
    VALUES (nextval('passport_id_seq'), '0123', '123456', 'ОВД г.Москвы №113-32', '1995-05-12', '033-235', 'Россия');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country)
    VALUES (nextval('passport_id_seq'), '4567', '456789', 'ОВД г.Калининграда', '1985-01-02', NULL, 'Россия');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country)
    VALUES (nextval('passport_id_seq'), '3568', '001122', 'ОВД г.Уфы №153-34', '2000-05-22', '042-112', 'Россия');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country)
    VALUES (nextval('passport_id_seq'), '0123', '102030', 'ОВД г.Калининграда', '1984-06-13', NULL, 'Россия');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country)
  VALUES (nextval('passport_id_seq'), '' , '123456', 'ОВД г.Москвы №113-32', '2003-03-19', NULL, 'Россия');

INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия', 'Москва', 'Москва', 'пр-т Мира', '122', NULL, '37', '131677');
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия', 'Московская обл.', 'Королев', 'Ленина', '27', 'А', '101', '141222');
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия', 'Башкортостан', 'Уфа', 'Рихарда Зорге', '12', NULL, '24', NULL);
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия', 'Московская обл.', 'Мытищи', 'Олимпийский пр-т', '43', 'Б', '99', NULL);
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия' , NULL, 'Москва', 'Солянка', '10', NULL, '3', NULL);

INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq)
    VALUES (nextval('contacts_id_seq'), '+7(922)543-67-66', 'ivanov@mail.ru', NULL, NULL, NULL, NULL, NULL);
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq)
    VALUES (nextval('contacts_id_seq'), '+7(916)453-37-26', 'maria33@yandex.ru', 'maria33', 'mashka33', NULL, NULL, NULL);
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq)
    VALUES (nextval('contacts_id_seq'), '+7(917)444-62-62', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq)
    VALUES (nextval('contacts_id_seq'), '+7(922)930-00-66', 'petrov@gmail.com', NULL, 'petushok', 'petya', NULL, NULL);
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq)
    VALUES (nextval('contacts_id_seq'), '+7(916)777-00-00' , 'medvedev@kreml.ru', NULL, NULL, 'medved', 'medved', NULL);

INSERT INTO masters(id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy)
    VALUES (nextval('masters_id_seq'), 'Иванов', 'Иван', 'Николаевич', '1971-10-02', '2015-03-09', 1, 1, 1, NULL, 1, FALSE);
INSERT INTO masters(id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy)
    VALUES (nextval('masters_id_seq'), 'Морозова', 'Мария', 'Александровна', '1977-06-27', '2015-03-22', 3, 2, 3, 3, 2, FALSE);
