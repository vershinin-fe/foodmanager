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

INSERT INTO users (name, email, password, role) VALUES
  ('FreeUser', 'freeuser@mail.ru', 'password', 'ROLE_USER');

INSERT INTO items (name, quantity, mu, description, price, closed, createdate, family_id) VALUES
  ('Молоко', 1, 'LITER', 'Чебаркуль', 84, false, '2018-01-14 15:00:50', 100),
  ('Хлеб', 1, 'PIECE', 'Бородинский', 30, false, '2018-01-14 15:00:50', 100),
  ('Кефир', 1, 'LITER', 'Чебаркуль', 89, false, '2018-01-14 15:00:50', 100),
  ('Творог', 500, 'GRAM', 'Чебаркуль', 125, false, '2018-01-14 15:00:50', 100),
  ('Сыр', 300, 'GRAM', 'Артур', 200, true, '2018-02-14 15:00:50', 100),
  ('Яблоки', 5, 'PIECE', 'Сезонные', 80, true, '2018-03-14 15:00:50', 100),
  ('Бананы', 10, 'PIECE', '', 60, false, '2018-04-14 15:00:50', 100),
  ('Курица', 1, 'KILOGRAM', 'Грудка', 150, true, '2017-03-14 15:00:50', 101),
  ('Макароны', 500, 'GRAM', 'С птичкой', 48, false, '2017-03-15 15:00:50', 101);