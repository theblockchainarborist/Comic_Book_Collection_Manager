package com.techelevator.dao;

public interface ComicDao {

    boolean addComicToCollection(int comicIdApi, int collectionId);

    boolean deleteComicFromCollection(int comicIdApi, int collectionId);

}
