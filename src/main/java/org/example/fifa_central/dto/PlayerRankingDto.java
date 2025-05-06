package org.example.fifa_central.dto;

import lombok.Data;

@Data
public class PlayerRankingDto {
    private Integer rank;
    private String id;
    private String name;
    private Integer number;
    private String position;
    private String nationality;
    private Integer age;
    private String championship;
    private Integer scoredGoals;
    private PlayingTimeDto playingTime;
}
