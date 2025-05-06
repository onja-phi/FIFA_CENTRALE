/*package org.example.fifa_central.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CentralService {
    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getBestPlayers(int top, String playingTimeUnit) {
        String sql = """
            SELECT p.*, ROW_NUMBER() OVER (ORDER BY scored_goals DESC, playing_time DESC) as rank
            FROM players p
            ORDER BY scored_goals DESC, playing_time DESC
            LIMIT ?
            """;
        return jdbcTemplate.queryForList(sql, top);
    }

    public List<Map<String, Object>> getBestClubs(int top) {
        String sql = """
            SELECT c.*, ROW_NUMBER() OVER (ORDER BY points DESC, (scored_goals - conceded_goals) DESC) as rank
            FROM clubs c
            ORDER BY points DESC, (scored_goals - conceded_goals) DESC
            LIMIT ?
            """;
        return jdbcTemplate.queryForList(sql, top);
    }

    public List<Map<String, Object>> getChampionshipRankings() {
        String sql = """
            SELECT championship,
                   PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY (scored_goals - conceded_goals)) as difference_goals_median,
                   ROW_NUMBER() OVER (ORDER BY PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY (scored_goals - conceded_goals))) as rank
            FROM clubs
            GROUP BY championship
            ORDER BY difference_goals_median
            """;
        return jdbcTemplate.queryForList(sql);
    }
}
*/