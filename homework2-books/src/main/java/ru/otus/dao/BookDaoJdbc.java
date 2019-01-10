package ru.otus.dao;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {

    private NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long authorId = rs.getLong("author_id");
            String authorFirstName = rs.getString("author_first_name");
            String authorLastName = rs.getString("author_last_name");
            long genreId = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");
            return new Book(id, name, new Author(authorId, authorFirstName, authorLastName), new Genre(genreId, genreName));
        }
    }

    private static String getBookQuery() {
        return "select b.id as id, b.name as name, a.id as author_id, " +
                "a.first_name as author_first_name, a.last_name as author_last_name, " +
                "g.id as genre_id, g.name as genre_name " +
                "from books b join authors a on b.author_id = a.id join genres g on b.genre_id = g.id";
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(getBookQuery(), new BookMapper());
    }

    @Override
    public Book getById(long id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject(getBookQuery() + "  where b.id = :id",
                params, new BookMapper());
    }

    @Override
    public List<Book> getByAuthorId(long authorId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        return jdbc.query(getBookQuery() + " where a.id = :authorId",
                params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    @Override
    public void insert(Book book) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("name", book.getName());
        params.put("authorId", book.getAuthor().getId());
        params.put("genreId", book.getGenre().getId());
        jdbc.update("insert into books (`id`, `name`, `author_id`, `genre_id`) values (:id, :name, :authorId, :genreId)", params);
    }

    @Override
    public void updateNameById(long id, String newName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("newName", newName);
        jdbc.update("update books set name = :newName where id = :id", params);
    }


}
