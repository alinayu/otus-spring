package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return new Author(id, firstName, lastName);
        }
    }

    @Override
    public Author getById(long id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return  jdbc.queryForObject("select * from authors where id = :id", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void insert(Author author) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("firstName", author.getFirstName());
        params.put("lastName", author.getLastName());
        jdbc.update("insert into authors (`id`, `first_name`, `last_name`) values (:id, :firstName, :lastName)", params);

    }

    @Override
    public void deleteById(long id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from authors where id = :id", params);
    }


}
