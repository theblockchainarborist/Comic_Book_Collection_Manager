package com.techelevator.model;

public class CollectionStats {

    private int totalNumberofComics;
    private String[] comicsPerSuperhero;
    private String[] comicsPerSeries;
    private ComicInfo oldestComicInCollection;


    public CollectionStats(){}

    public int getTotalNumberofComics() {
        return totalNumberofComics;
    }

    public void setTotalNumberofComics(int totalNumberofComics) {
        this.totalNumberofComics = totalNumberofComics;
    }

    public String[] getComicsPerSuperhero() {
        return comicsPerSuperhero;
    }

    public void setComicsPerSuperhero(String[] comicsPerSuperhero) {
        this.comicsPerSuperhero = comicsPerSuperhero;
    }

    public String[] getComicsPerSeries() {
        return comicsPerSeries;
    }

    public void setComicsPerSeries(String[] comicsPerSeries) {
        this.comicsPerSeries = comicsPerSeries;
    }

    public ComicInfo getOldestComicInCollection() {
        return oldestComicInCollection;
    }

    public void setOldestComicInCollection(ComicInfo oldestComicInCollection) {
        this.oldestComicInCollection = oldestComicInCollection;
    }

}
