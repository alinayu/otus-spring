DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
CREATE TABLE authors(id BIGINT generated by default as identity, first_name VARCHAR(255), last_name VARCHAR(255));
CREATE TABLE genres(id BIGINT generated by default as identity, name VARCHAR(255));
CREATE TABLE books(id BIGINT generated by default as identity, name VARCHAR(255), author_id BIGINT, genre_id BIGINT,
                   FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
                   FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
);
CREATE TABLE comments(id BIGINT generated by default as identity, text VARCHAR(255), book_id BIGINT,
                   FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

