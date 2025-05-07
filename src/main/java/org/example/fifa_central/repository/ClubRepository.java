package org.example.fifa_central.repository;

import lombok.RequiredArgsConstructor;
import org.example.fifa_central.dto.ClubStatisticDto;
import org.example.fifa_central.model.Club;
import org.example.fifa_central.model.ClubStatistic;
import org.example.fifa_central.model.Coach;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
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

        public List<ClubStatistic> getClubsStatistics() {
            String sql = "SELECT cs.club_id, c.name AS club_name, c.acronym, c.year_creation, c.stadium, co.name AS coach_name, co.nationality AS coach_nationality, " +
                    "cs.ranking_points, cs.scored_goals, cs.conceded_goals, cs.difference_goals," +
                    " cs.clean_sheet_number " +
                    "FROM club_statistics cs " +
                    "JOIN club c " +
                    "ON cs.club_id = c.id " +
                    "JOIN coach co ON c.coach_id = co.id " +
                    "ORDER BY cs.ranking_points DESC\n";
            List<ClubStatistic> results = jdbc.query(sql, (rs, rowNum) -> {
                Coach coach = new Coach();
                coach.setName(rs.getString("coach_name"));
                coach.setNationality(rs.getString("coach_nationality"));

                Club club = new Club(
                        rs.getString("club_id"),
                        rs.getString("club_name"),
                        rs.getString("acronym"),
                        rs.getInt("year_creation"),
                        rs.getString("stadium")
                );

                club.setCoach(coach);

                ClubStatistic dto = new ClubStatistic();
                dto.setRankingPoints(rowNum);
                dto.setClub(club);
                dto.setRankingPoints(rs.getInt("ranking_points"));
                dto.setScoredGoals(rs.getInt("scored_goals"));
                dto.setConcededGoals(rs.getInt("conceded_goals"));
                dto.setDifferenceGoals(rs.getInt("difference_goals"));
                dto.setCleanSheetNumber(rs.getInt("clean_sheet_number"));

                return dto;
            });

            return results;
        }
    }



