DROP TABLE IF EXISTS books;
CREATE TABLE books(id BIGINT generated by default as identity, name VARCHAR(255));

DROP TABLE IF EXISTS users;
CREATE TABLE users(id BIGINT generated by default as identity, username VARCHAR(255) not null unique, password VARCHAR(255));
