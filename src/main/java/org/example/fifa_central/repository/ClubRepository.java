package org.example.fifa_central.repository;

import lombok.RequiredArgsConstructor;
import org.example.fifa_central.model.Club;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClubRepository {

    private final JdbcTemplate jdbc;

    public ClubRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Club club) {
        String sql = "INSERT INTO clubs (id, name, acronym, year_creation, stadium) VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name";
        jdbc.update(sql,
                club.getId(),
                club.getName(),
                club.getAcronym(),
                club.getYearCreation(),
                club.getStadium()
        );
    }
}

