package com.gmul.toy.example.helper;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserGivenHelper {
    private JdbcTemplate jdbcTemplate;

    public UserGivenHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void givenUser(String id, String pw, String email) {
        jdbcTemplate.update(
                "insert into user value (?, ?, ?)" +
                        "on duplicate key update password = ?, email = ?"
                , id, pw, email, pw, email);
    }
}
