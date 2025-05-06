package org.example.fifa_central.dto;

import lombok.Data;

@Data
public class PlayerStatisticsDto {
    private String id;
    private Integer scoredGoals;
    private Double playingTimeValue;
    private String playingTimeUnit;

    // Explicit getters if Lombok is not working
    public String getId() {
        return id;
    }

    public Integer getScoredGoals() {
        return scoredGoals;
    }

    public Double getPlayingTimeValue() {
        return playingTimeValue;
    }

    public String getPlayingTimeUnit() {
        return playingTimeUnit;
    }
}