package org.example.fifa_central.repository;

import org.example.fifa_central.model.Coach;
import org.example.fifa_central.model.Player;
import org.example.fifa_central.model.Club;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SynchronizationRepository {
    private final JdbcTemplate jdbcTemplate;

    public SynchronizationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveCoachData(Coach coach) {
        if (coach == null) return;

        jdbcTemplate.update("""
        INSERT INTO coaches (name, nationality)
        VALUES (?, ?)
        ON CONFLICT (name) DO UPDATE SET
        nationality = EXCLUDED.nationality
    """,
                coach.getName(),
                coach.getNationality()
        );
    }

    public void saveClubData(Club club) {
        if (club == null) return;

        jdbcTemplate.update("""
            INSERT INTO clubs (id, name, acronym, year_creation, stadium)
            VALUES (?, ?, ?, ?, ?)
            ON CONFLICT (id) DO UPDATE SET
            name = EXCLUDED.name,
            acronym = EXCLUDED.acronym,
            year_creation = EXCLUDED.year_creation,
            stadium = EXCLUDED.stadium
            """,
                club.getId(),
                club.getName(),
                club.getAcronym(),
                club.getYearCreation(),
                club.getStadium()
        );
    }


    public void savePlayerData(Player player) {
        String clubId = (player.getClub() != null) ? player.getClub().getId() : null;
        String coachName = (player.getClub() != null && player.getClub().getCoach() != null)
                ? player.getClub().getCoach().getName() : null;

        jdbcTemplate.update("""
        INSERT INTO players (id, name, number, position, nationality, age, club_id, coach_name)
        VALUES (?, ?, ?, CAST(? AS player_position), ?, ?, ?, ?)
        ON CONFLICT (id) DO UPDATE SET
            name = EXCLUDED.name,
            number = EXCLUDED.number,
            position = EXCLUDED.position,
            nationality = EXCLUDED.nationality,
            age = EXCLUDED.age,
            club_id = EXCLUDED.club_id,
            coach_name = EXCLUDED.coach_name
        """,
                player.getId(),
                player.getName(),
                player.getNumber(),
                player.getPosition().name(),
                player.getNationality(),
                player.getAge(),
                clubId,
                coachName
        );
    }



    public void updateChampionshipRankings(String championship, Double differenceGoalsMedian, Integer rank) {
        jdbcTemplate.update("""
            INSERT INTO championship_rankings (championship, difference_goals_median, rank)
            VALUES (CAST(? AS championship), ?, ?)
            ON CONFLICT (championship) DO UPDATE SET
            difference_goals_median = EXCLUDED.difference_goals_median,
            rank = EXCLUDED.rank
            """,
                championship,
                differenceGoalsMedian,
                rank
        );
    }
}