package org.example.fifa_central.model;

import lombok.*;

import java.util.Objects;

public class ClubStatistic {

    private String id;
    private Club club;
    private int rankingPoints;
    private int scoredGoals;
    private int concededGoals;
    private int differenceGoals;
    private int cleanSheetNumber;
    private int yearCreation;
    private String stadium;
    private Coach coach;

    public ClubStatistic(String id, Club club, int rankingPoints, int scoredGoals, int concededGoals, int differenceGoals, int cleanSheetNumber, int yearCreation, String stadium, Coach coach) {
        this.id = id;
        this.club = club;
        this.rankingPoints = rankingPoints;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.differenceGoals = differenceGoals;
        this.cleanSheetNumber = cleanSheetNumber;
        this.yearCreation = yearCreation;
        this.stadium = stadium;
        this.coach = coach;
    }

    public ClubStatistic() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(int rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getConcededGoals() {
        return concededGoals;
    }

    public void setConcededGoals(int concededGoals) {
        this.concededGoals = concededGoals;
    }

    public int getDifferenceGoals() {
        return differenceGoals;
    }

    public void setDifferenceGoals(int differenceGoals) {
        this.differenceGoals = differenceGoals;
    }

    public int getCleanSheetNumber() {
        return cleanSheetNumber;
    }

    public void setCleanSheetNumber(int cleanSheetNumber) {
        this.cleanSheetNumber = cleanSheetNumber;
    }

    public int getYearCreation() {
        return yearCreation;
    }

    public void setYearCreation(int yearCreation) {
        this.yearCreation = yearCreation;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubStatistic that = (ClubStatistic) o;
        return rankingPoints == that.rankingPoints && scoredGoals == that.scoredGoals && concededGoals == that.concededGoals && differenceGoals == that.differenceGoals && cleanSheetNumber == that.cleanSheetNumber && yearCreation == that.yearCreation && Objects.equals(id, that.id) && Objects.equals(club, that.club) && Objects.equals(stadium, that.stadium) && Objects.equals(coach, that.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, club, rankingPoints, scoredGoals, concededGoals, differenceGoals, cleanSheetNumber, yearCreation, stadium, coach);
    }

    @Override
    public String toString() {
        return "ClubStatistic{" +
                "id='" + id + '\'' +
                ", club=" + club +
                ", rankingPoints=" + rankingPoints +
                ", scoredGoals=" + scoredGoals +
                ", concededGoals=" + concededGoals +
                ", differenceGoals=" + differenceGoals +
                ", cleanSheetNumber=" + cleanSheetNumber +
                ", yearCreation=" + yearCreation +
                ", stadium='" + stadium + '\'' +
                ", coach=" + coach +
                '}';
    }


    // Getters, setters, etc.
}

