package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }

    @Override
    public Genre getById(long id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return  jdbc.queryForObject("select * from genres where id = :id", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    @Override
    public void insert(Genre genre) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        jdbc.update("insert into genres (`id`, `name`) values (:id, :name)", params);
    }

    @Override
    public void deleteById(long id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from genres where id = :id", params);
    }
}
