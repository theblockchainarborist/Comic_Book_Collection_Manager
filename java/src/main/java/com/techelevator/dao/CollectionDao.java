package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.ComicInfo;

import java.util.List;

public interface CollectionDao {

    Collection getCollectionById(int id);

    List<Collection> getPublicCollections();

    List<Collection> getUserCollections(int userId);

    List<Integer> getComicsByCollectionId(int collectionId);

    boolean createCollection(Collection collection);

    boolean deleteCollection(int collectionId);

}
