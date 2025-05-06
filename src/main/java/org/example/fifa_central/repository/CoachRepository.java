package org.example.fifa_central.repository;

import lombok.RequiredArgsConstructor;
import org.example.fifa_central.model.Coach;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
//@RequiredArgsConstructor
public class CoachRepository {

    private final JdbcTemplate jdbc;

    public CoachRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(String name, Coach coach) {
        String sql = "INSERT INTO coaches (name, nationality) VALUES (?, ?)"+
        "ON CONFLICT (club_id) DO UPDATE SET name = EXCLUDED.name";
        jdbc.update(sql, coach.getName(), coach.getNationality());

    }
}

