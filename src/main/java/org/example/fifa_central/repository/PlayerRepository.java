package org.example.fifa_central.repository;

import lombok.RequiredArgsConstructor;
import org.example.fifa_central.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
//@RequiredArgsConstructor
public class PlayerRepository {

    private final JdbcTemplate jdbc;

    public PlayerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Player player) {
        String sql = "INSERT INTO players (id, name, number, position, nationality, age, club_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, age = EXCLUDED.age";
        jdbc.update(sql,
                player.getId(),
                player.getName(),
                player.getNumber(),
                player.getPosition(),
                player.getNationality(),
                player.getAge(),
                player.getClub().getId()
        );
    }
}

