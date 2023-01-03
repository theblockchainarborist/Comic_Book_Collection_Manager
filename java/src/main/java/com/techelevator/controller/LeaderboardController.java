package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.StatsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Leaderboard;
import com.techelevator.services.RestMarvelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LeaderboardController {

    private final CollectionDao collectionDao;
    private final UserDao userDao;
    private final RestMarvelService api;
    private final StatsDao statsDao;

    public LeaderboardController(CollectionDao collectionDao, UserDao userDao, StatsDao statsDao) {
        this.collectionDao = collectionDao;
        this.userDao = userDao;
        this.api = new RestMarvelService();
        this.statsDao = statsDao;
    }

    @RequestMapping(path = "/leaderboard", method = RequestMethod.GET)
    public Leaderboard usersWithMostCollections() {
        Leaderboard result = new Leaderboard();

        result.setUserWithMostCollections(statsDao.userWithMostCollections());
        result.setUserWithMostComics(statsDao.userWithMostComics());
        return result;

    }


}
