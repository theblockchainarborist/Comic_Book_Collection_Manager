package com.techelevator.dao;

public interface StatsDao {

    int totalNumberOfCollections();

    int highestNumberOfComicsInCollection();

    String[] userWithMostComics();

    String[] userWithMostCollections();

}
