package org.example.fifa_central.dto;

import lombok.Data;

@Data
public class ClubStatisticsDto {
    private String id;
    private String clubId;
    private String seasonId;
    private Integer rankingPoints;
    private Integer scoredGoals;
    private Integer concededGoals;
    private Integer differenceGoals;
    private Integer cleanSheetNumber;
}
