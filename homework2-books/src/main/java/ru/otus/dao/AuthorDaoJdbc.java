package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return new Author(id, firstName, lastName);
        }
    }
}
