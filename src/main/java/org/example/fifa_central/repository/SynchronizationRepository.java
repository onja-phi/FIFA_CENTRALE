package org.example.fifa_central.repository;

import org.example.fifa_central.dto.ClubStatisticDto;
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
            INSERT INTO clubs (id, name, acronym, year_creation, stadium, coach_name)
            VALUES (?, ?, ?, ?, ?, ?)
            ON CONFLICT (id) DO UPDATE SET
            name = EXCLUDED.name,
            acronym = EXCLUDED.acronym,
            year_creation = EXCLUDED.year_creation,
            stadium = EXCLUDED.stadium,
            coach_name = EXCLUDED.coach_name
            """,
                club.getId(),
                club.getName(),
                club.getAcronym(),
                club.getYearCreation(),
                club.getStadium(),
                club.getCoach().getName()
        );
        saveCoachData(club.getCoach());
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

    public void saveClubStatisticsData(ClubStatisticDto dto) {
        if (dto == null) return;

        jdbcTemplate.update("""
        INSERT INTO club_statistics (
            club_id,
            ranking_points,
            scored_goals,
            conceded_goals,
            difference_goals,
            clean_sheet_number,
            year_creation,
            stadium,
            coach_name,
            coach_nationality
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (club_id) DO UPDATE SET
            ranking_points = EXCLUDED.ranking_points,
            scored_goals = EXCLUDED.scored_goals,
            conceded_goals = EXCLUDED.conceded_goals,
            difference_goals = EXCLUDED.difference_goals,
            clean_sheet_number = EXCLUDED.clean_sheet_number,
            year_creation = EXCLUDED.year_creation,
            stadium = EXCLUDED.stadium,
            coach_name = EXCLUDED.coach_name,
            coach_nationality = EXCLUDED.coach_nationality
        """,
                dto.getId(),
                dto.getRankingPoints(),
                dto.getScoredGoals(),
                dto.getConcededGoals(),
                dto.getDifferenceGoals(),
                dto.getCleanSheetNumber(),
                dto.getYearCreation(),
                dto.getStadium(),
                dto.getCoach().getName(),
                dto.getCoach().getNationality()
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