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

INSERT INTO posts_services(id, post_id, service_id) VALUES (nextval('posts_services_id_seq'), 2, 3);
INSERT INTO posts_services(id, post_id, service_id) VALUES (nextval('posts_services_id_seq'), 2, 5);
INSERT INTO posts_services(id, post_id, service_id) VALUES (nextval('posts_services_id_seq'), 1, 5);

INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country, bind_by)
    VALUES (nextval('passport_id_seq'), '0123', '123456', 'ОВД г.Москвы №113-32', '1995-05-12', '033-235', 'Россия', 'MASTER');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country, bind_by)
    VALUES (nextval('passport_id_seq'), '4567', '456789', 'ОВД г.Калининграда', '1985-01-02', NULL, 'Россия', 'MASTER');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country, bind_by)
    VALUES (nextval('passport_id_seq'), '3568', '001122', 'ОВД г.Уфы №153-34', '2000-05-22', '042-112', 'Россия', 'MASTER');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country, bind_by)
    VALUES (nextval('passport_id_seq'), '0123', '102030', 'ОВД г.Калининграда', '1984-06-13', NULL, 'Россия', 'CLIENT');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country, bind_by)
  VALUES (nextval('passport_id_seq'), '' , '123456', 'ОВД г.Москвы №113-32', '2003-03-19', NULL, 'Россия', 'CLIENT');
INSERT INTO passport(id, series, num, issued_by, issue_date, subdivision, country, bind_by)
    VALUES (nextval('passport_id_seq'), '4233' , '4567', 'ОВД г.Москвы №043-32', '2007-05-19', NULL, 'Россия', 'CLIENT');

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
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия' , 'Московская обл.', 'Королев', 'Макаренко', '3', NULL, '5', NULL);
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия' , 'Московская обл.', 'Королев', 'Макаренко', '3', NULL, '5', NULL);
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия' , 'Воронежская обл.', 'Воронеж', 'проспект Ленина', '27', 'А', '44', NULL);
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия' , NULL, 'Москва', 'Сиреневый бульвар', '33', NULL, '24', NULL);
INSERT INTO address(id, country, district, city, street, house, section, flat, zip)
    VALUES (nextval('address_id_seq'), 'Россия' , NULL, 'Москва', '3-я магистральная', '115', NULL, '77', NULL);

INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(922)543-67-66', 'ivanov@mail.ru', NULL, NULL, NULL, NULL, NULL, 'MASTER');
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(916)453-37-26', 'maria33@yandex.ru', 'maria33', 'mashka33', NULL, NULL, NULL, 'MASTER');
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(917)444-62-62', NULL, NULL, NULL, NULL, NULL, NULL, 'MASTER');
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(922)930-00-66', 'chutov@gmail.com', NULL, 'chutik', 'chut', NULL, NULL, 'CLIENT');
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(916)777-00-00' , 'shvedov@sberbank.ru', NULL, NULL, 'sanyok', 'sancho', NULL, 'CLIENT');
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(916)423-45-78' , 'egorova@mail.ru', NULL, NULL, NULL , NULL, NULL, 'MASTER');
INSERT INTO contacts(id, phone, email, vk, skype, facebook, twitter, icq, bind_by)
    VALUES (nextval('contacts_id_seq'), '+7(926)653-34-84' , 'bichuk@yandex.ru', NULL, NULL, 'bichuk' , NULL, NULL, 'CLIENT');

INSERT INTO masters(id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy)
    VALUES (nextval('masters_id_seq'), 'Иванов', 'Иван', 'Николаевич', '1971-10-02', '2015-03-09', 1, 1, 1, NULL, 1, FALSE);
INSERT INTO masters(id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy)
    VALUES (nextval('masters_id_seq'), 'Морозова', 'Мария', 'Александровна', '1977-06-27', '2015-03-22', 3, 2, 3, 3, 2, FALSE);
INSERT INTO masters(id, surname, name, patronymic, birth_date, hiring_date, id_passport, id_post, id_reg_address, id_live_address, id_contact, busy)
    VALUES (nextval('masters_id_seq'), 'Егорова', 'Нина', 'Петровна', '1967-06-07', '2015-05-26', 2, 1, 4, 4, 6, TRUE);

INSERT INTO clients(id, surname, name, patronymic, birth_date, registration_date, id_passport, id_reg_address, id_live_address, id_contact, id_discount, in_black)
    VALUES (nextval('clients_id_seq'), 'Чутов', 'Сергей', 'Александрович', '1979-06-04', '2015-03-20', 4, 6, 7, 4, NULL, TRUE);
INSERT INTO clients(id, surname, name, patronymic, birth_date, registration_date, id_passport, id_reg_address, id_live_address, id_contact, id_discount, in_black)
    VALUES (nextval('clients_id_seq'), 'Шведов', 'Александр', 'Вячеславович', '1978-09-10', '2015-03-25', 5, 8, 9, 5, 1, FALSE);
INSERT INTO clients(id, surname, name, patronymic, birth_date, registration_date, id_passport, id_reg_address, id_live_address, id_contact, id_discount, in_black)
    VALUES (nextval('clients_id_seq'), 'Бичук', 'Сергей', 'Григорьевич', '1988-09-10', '2015-06-25', 6, 10, 10, 7, NULL, FALSE);

INSERT INTO schedule(id, appoint_date, appoint_time, master_id, client_id, service_id, note, status)
    VALUES (nextval('schedule_id_seq'), '2016-05-10', 750, 1, 1, 5, 'Отменен клиентом', 'CANCELLED');
INSERT INTO schedule(id, appoint_date, appoint_time, master_id, client_id, service_id, note, status)
    VALUES (nextval('schedule_id_seq'), '2016-05-10', 600, 1, 1, 5, NULL, 'DONE');
INSERT INTO schedule(id, appoint_date, appoint_time, master_id, client_id, service_id, note, status)
    VALUES (nextval('schedule_id_seq'), '2016-05-10', 930, 2, 2, 3, NULL, 'DONE');
INSERT INTO schedule(id, appoint_date, appoint_time, master_id, client_id, service_id, note, status)
    VALUES (nextval('schedule_id_seq'), '2016-05-11', 840, 1, 2, 5, NULL, 'CREATED');
INSERT INTO schedule(id, appoint_date, appoint_time, master_id, client_id, service_id, note, status)
    VALUES (nextval('schedule_id_seq'), '2016-05-11', 870, 2, 1, 3, NULL, 'CREATED');