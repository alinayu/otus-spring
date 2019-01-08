package ru.otus.dao;

import ru.otus.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

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
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int authorId = rs.getInt("author_id");
            int genreId = rs.getInt("genre_id");
            return new Book(id, name, authorId, genreId);
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public Book getById(int id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject("select * from books where id = :id", params, new BookMapper());
    }

    @Override
    public List<Book> getByAuthorId(int authorId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("authorId", authorId);
        return jdbc.query("select b.* from books b join authors a on b.author_id = a.id where a.id = :authorId",
                params, new BookMapper());
    }

    @Override
    public void deleteById(int id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    @Override
    public void insert(int id, String name, int authorId, int genreId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("authorId", authorId);
        params.put("genreId", genreId);
        jdbc.update("insert into books (`id`, `name`, `author_id`, `genre_id`) values (:id, :name, :authorId, :genreId)", params);
    }

    @Override
    public void updateNameById(int id, String newName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("newName", newName);
        jdbc.update("update books set name = :newName where id = :id", params);
    }


}
