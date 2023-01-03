package com.techelevator.model;

public class Leaderboard {

    private String[] usersWithMostComics;
    private String[] usersWithMostCollections;

    public Leaderboard(){}

    public String[] getUserWithMostComics() {
        return usersWithMostComics;
    }

    public void setUserWithMostComics(String[] userWithMostComics) {
        this.usersWithMostComics = userWithMostComics;
    }

    public String[] getUserWithMostCollections() {
        return usersWithMostCollections;
    }

    public void setUserWithMostCollections(String[] userWithMostCollections) {
        this.usersWithMostCollections = userWithMostCollections;
    }
}
