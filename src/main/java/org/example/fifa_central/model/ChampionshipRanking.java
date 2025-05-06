package org.example.fifa_central.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChampionshipRanking {
    private String championship;
    private Double differenceGoalsMedian;
    private Integer rank;
}
