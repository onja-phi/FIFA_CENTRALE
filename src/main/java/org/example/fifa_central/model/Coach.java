package org.example.fifa_central.model;

public class Coach {
    private String name;
    private String nationality;

    public Coach() {
    }

    public Coach(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
