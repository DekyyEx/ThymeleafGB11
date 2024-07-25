package org.example.springmvcthymeleaf.repository;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.example.springmvcthymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepository {

    JdbcTemplate jdbc;
    QueryProperties sql;

    public UserRepository(JdbcTemplate jdbc, QueryProperties sql) {
        this.jdbc = jdbc;
        this.sql = sql;
    }

    public List<User> findAll() {
        RowMapper<User> userRowMapper = ((rs, rowNum) -> {
            User rowObject = new User();
            rowObject.setId(rs.getInt("id"));
            rowObject.setFirstName(rs.getString("firstName"));
            rowObject.setLastName(rs.getString("lastName"));
            return rowObject;
        });

        return jdbc.query(sql.getSqlFindAll(), userRowMapper);
    }

    public void save(@NonNull User user) {
        jdbc.update(sql.getSqlSave(), user.getFirstName(), user.getLastName());
    }

    public void deleteById(int id) {
        jdbc.update(sql.getSqlDeleteById(), id);
    }

    public void updateUser(@NonNull User user) {
        jdbc.update(sql.getSqlUpdateUser(), user.getFirstName(), user.getLastName(), user.getId());
    }

    public User getOne(int id) {
        String sql_string = sql.getSqlGetOne() + id;
        RowMapper<User> userRowMapper = ((rs, rowNum) -> {
            User rowObject = new User();
            rowObject.setId(rs.getInt("id"));
            rowObject.setFirstName(rs.getString("firstName"));
            rowObject.setLastName(rs.getString("lastName"));
            return rowObject;
        });
        List<User> users = jdbc.query(sql_string, userRowMapper);
        if (users.isEmpty()) {
            return null;
        } else {
            return jdbc.query(sql_string, userRowMapper).get(0);
        }
    }
}
