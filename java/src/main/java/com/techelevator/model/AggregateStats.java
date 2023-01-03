package com.techelevator.model;

public class AggregateStats {

    private int totalNumberOfUsers;
    private int totalNumberOfCollections;
    private int totalComicsInAllCollections;
    private int highestNumberOfComicsInCollection;

    public AggregateStats() {}

    public int getTotalNumberOfUsers() {
        return totalNumberOfUsers;
    }

    public void setTotalNumberOfUsers(int totalNumberOfUsers) {
        this.totalNumberOfUsers = totalNumberOfUsers;
    }

    public int getTotalNumberOfCollections() {
        return totalNumberOfCollections;
    }

    public void setTotalNumberOfCollections(int totalNumberOfCollections) {
        this.totalNumberOfCollections = totalNumberOfCollections;
    }

    public int getTotalComicsInAllCollections() {
        return totalComicsInAllCollections;
    }

    public void setTotalComicsInAllCollections(int totalComicsInAllCollections) {
        this.totalComicsInAllCollections = totalComicsInAllCollections;
    }

    public int getHighestNumberOfComicsInCollection() {
        return highestNumberOfComicsInCollection;
    }

    public void setHighestNumberOfComicsInCollection(int highestNumberOfComicsInCollection) {
        this.highestNumberOfComicsInCollection = highestNumberOfComicsInCollection;
    }
}
