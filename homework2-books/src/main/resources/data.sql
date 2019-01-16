insert into authors (id, `last_name`, `first_name`) values
(1, 'Пушкин', 'Александр'),
(2, 'Толстой', 'Лев');

insert into genres (id, `name`) values
(1, 'Повесть'),
(2, 'Роман');

insert into books(id, `name`, author_id, genre_id) values
(1, 'Война и мир', 2, 2),
(2, 'Капитанская дочка', 1, 1);