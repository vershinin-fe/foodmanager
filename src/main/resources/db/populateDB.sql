DELETE FROM items;
DELETE FROM users;
DELETE FROM families;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100;

INSERT INTO families (name) VALUES
  ('Вершинины'),
  ('Дерябины');

INSERT INTO users (name, email, password, role, family_id) VALUES
  ('Few', 'few@mail.ru', 'password', 'ROLE_USER', 100),
  ('Zoidberg', 'zoidberg@mail.ru', 'password', 'ROLE_USER', 101);

INSERT INTO users (id, name, email, password, role) VALUES
  (1, 'Admin', 'admin@mail.ru', 'password', 'ROLE_ADMIN');

INSERT INTO items (name, quantity, description, price, closed, createdate, family_id) VALUES
  ('Молоко', 1, 'Чебаркуль', 84, false, '2018-01-14 15:00:50', 100),
  ('Хлеб', 1, 'Бородинский', 30, false, '2018-01-14 15:00:50', 100),
  ('Кефир', 1, 'Чебаркуль', 89, false, '2018-01-14 15:00:50', 100),
  ('Творог', 1, 'Чебаркуль', 125, false, '2018-01-14 15:00:50', 100),
  ('Сыр', 1, 'Артур', 200, true, '2018-02-14 15:00:50', 100),
  ('Яблоки', 5, 'Сезонные', 80, true, '2018-03-14 15:00:50', 100),
  ('Бананы', 10, '', 60, false, '2018-04-14 15:00:50', 100),
  ('Курица', 1, 'Грудка', 150, true, '2017-03-14 15:00:50', 101),
  ('Макароны', 1, 'С птичкой', 48, false, '2017-03-15 15:00:50', 101);