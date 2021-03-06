insert into authors (id, `last_name`, `first_name`) values
(1, 'Пушкин', 'Александр'),
(2, 'Толстой', 'Лев'),
(3, 'Макконнелл', 'Стив'),
(4, 'Мартин', 'Роберт');

insert into genres (id, `name`) values
(1, 'Повесть'),
(2, 'Роман'),
(3, 'Компьютерная литература');

insert into books(id, `name`, author_id, genre_id) values
(1, 'Война и мир', 2, 2),
(2, 'Капитанская дочка', 1, 1),
(3, 'Евгений Онегин', 1, 2),
(4, 'Совершенный код', 3, 3),
(5, 'Чистый код', 4, 3);

insert into comments(id, `text`, book_id) values
(1, 'excellent', 1);