package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.StatsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import com.techelevator.model.Collection;
import com.techelevator.services.RestMarvelService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.*;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class StatsController {

    private final StatsDao statsDao;
    private final CollectionDao collectionDao;
    private final UserDao userDao;
    private final RestMarvelService marvelApi;

    public StatsController(StatsDao statsDao, CollectionDao collectionDao, UserDao userDao) {
        this.statsDao = statsDao;
        this.collectionDao = collectionDao;
        this.userDao = userDao;
        this.marvelApi = new RestMarvelService();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/stats", method = RequestMethod.GET)
    public AggregateStats getAggregateStats() {
        AggregateStats results = new AggregateStats();
        results.setTotalNumberOfUsers(getNumberOfUsers());
        results.setTotalNumberOfCollections(getTotalNumberOfCollections());
        results.setTotalComicsInAllCollections(getTotalComicsInAllCollections());
        results.setHighestNumberOfComicsInCollection(getHighestNumberOfComicsInCollection());

        return results;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/stats/{collectionId}", method = RequestMethod.GET)
    public CollectionStats getCollectionStats(@PathVariable int collectionId) {
        CollectionStats results = new CollectionStats();
        List<ComicInfo> comicsList = getComicsinCollection(collectionId);

        if (comicsList.size() > 0) {
            results.setTotalNumberofComics(getTotalNumberofComics(collectionId));
            results.setComicsPerSuperhero(getComicsPerSuperhero(comicsList));
            results.setComicsPerSeries(getComicsPerSeries(comicsList));
            results.setOldestComicInCollection(getOldestComic(comicsList));
        }
        return results;
    }

    // AGGREGATE STATS

    public int getNumberOfUsers() {
        return userDao.findAll().size();
    }

    public int getTotalNumberOfCollections() {
        return statsDao.totalNumberOfCollections();
    }

    // Gets count of all comics including duplicates in collections
    public int getTotalComicsInAllCollections() {
        return getAllComicsInAllCollections().size();
    }

    public int getHighestNumberOfComicsInCollection() {
        return statsDao.highestNumberOfComicsInCollection();
    }

    // COLLECTION STATS
    public int getTotalNumberofComics(int collectionId) {
        return collectionDao.getComicsByCollectionId(collectionId).size();
    }

    public String[] getComicsPerSuperhero(List<ComicInfo> comicsList) {
        Map<String, Integer> superheroCount = new TreeMap<>();

        // Map of how many comics each character appears in within current collection
        for (ComicInfo comic : comicsList) {
            List<CharacterInfo> charactersList = comic.getCharacters().getItems();
            for (CharacterInfo character : charactersList) {
                if (superheroCount.containsKey(character.getName())) {
                    superheroCount.put(character.getName(), superheroCount.get(character.getName()) + 1);
                }
                else {
                    superheroCount.put(character.getName(), 1);
                }
            }
        }
        return convertMapToString(superheroCount);
    }

    public String[] getComicsPerSeries(List<ComicInfo> comicsList) {
        Map<String, Integer> seriesCount = new TreeMap<>();

        for (ComicInfo comic : comicsList) {
            String seriesName = comic.getSeries().getName();
            if (seriesCount.containsKey(seriesName)) {
                seriesCount.put(seriesName, seriesCount.get(seriesName) + 1);
            }
            else {
                seriesCount.put(seriesName, 1);
            }
        }
        return convertMapToString(seriesCount);
    }

    public ComicInfo getOldestComic(List<ComicInfo> comicsList) {
        ComicInfo oldestComic = comicsList.get(0);
        String earliestDate = oldestComic.getDates().get(0).getDate();

        for (ComicInfo comic : comicsList) {
            String currentDate = comic.getDates().get(0).getDate();
            if (currentDate.compareTo(earliestDate) < 0) {
                oldestComic = comic;
            }
        }
        return oldestComic;
    }

    public List<ComicInfo> getComicsinCollection(int collectionId) {
        List<Integer> comicIds = collectionDao.getComicsByCollectionId(collectionId);
        List<ComicInfo> comicsList = new ArrayList<>();

        for (int id : comicIds) {
            ComicInfo current = marvelApi.getComicById(id).getData().getResults().get(0);
            comicsList.add(current);
        }
        return comicsList;
    }

    public List<Integer> getAllComicsInAllCollections() {
        List<User> allUsers = userDao.findAll();
        List<Integer> results = new ArrayList<>();

        for (User user: allUsers) {
            List<Collection> userCollections = collectionDao.getUserCollections(user.getId());
            for (Collection collection : userCollections) {
                List<Integer> comicIds = collectionDao.getComicsByCollectionId(collection.getId());
                results.addAll(comicIds);
            }
        }
        return results;
    }

    public String[] convertMapToString(Map<String, Integer> statsMap) {
        String[] keys = statsMap.keySet().toArray(new String[0]);
        Integer[] values = statsMap.values().toArray(new Integer[0]);

        String[] results = new String[keys.length];
        for (int i=0; i < keys.length; i++) {
            results[i] = keys[i] + " : " + values[i];
        }
        return results;
    }

}
