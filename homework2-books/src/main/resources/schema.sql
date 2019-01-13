DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
CREATE TABLE authors(id INT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255));
CREATE TABLE genres(id INT PRIMARY KEY, name VARCHAR(255));
CREATE TABLE books(id INT PRIMARY KEY, name VARCHAR(255), author_id INT, genre_id INT,
                   FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
                   FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE
                  );
