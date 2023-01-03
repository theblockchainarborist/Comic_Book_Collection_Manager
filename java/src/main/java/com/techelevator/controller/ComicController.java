package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.ComicDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicBookDB;
import com.techelevator.model.ComicInfo;
import com.techelevator.services.RestMarvelService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class ComicController {

    private final RestMarvelService api;
    private final ComicDao comicDao;
    private final CollectionDao collectionDao;
    private final UserDao userDao;

    public ComicController(ComicDao comicDao, CollectionDao collectionDao, UserDao userDao) {
        this.api = new RestMarvelService();
        this.comicDao = comicDao;
        this.collectionDao = collectionDao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path="/comics/{comicId}", method = RequestMethod.GET)
    public ComicInfo getComicDetails(@PathVariable int comicId) {
        return api.getComicById(comicId).getData().getResults().get(0);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/collections/{collectionId}", method = RequestMethod.POST)
    public boolean addComicToCollection(@PathVariable int collectionId, @RequestBody ComicBookDB comicToAdd, Principal principal) {
        if (verifyUserIdentity(collectionId, principal)) {
            return comicDao.addComicToCollection(comicToAdd.getApiId(), collectionId);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You may only add comics to your own collection.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/collections/{collectionId}", method = RequestMethod.DELETE)
    public boolean deleteComicFromCollection(@PathVariable int collectionId, @RequestBody ComicBookDB comicToDelete, Principal principal) {
        if (verifyUserIdentity(collectionId, principal)) {
            return comicDao.deleteComicFromCollection(collectionId, comicToDelete.getApiId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have permission to delete this comic.");
        }
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/comics", method = RequestMethod.GET)
    public List<ComicInfo> searchComicsByTitle(@RequestParam String title) {
        Comic results = api.searchComicsByTitle(title);
        List<ComicInfo> comicsList = new ArrayList<>(results.getData().getResults());
        return comicsList;
    }

    public boolean verifyUserIdentity(int collectionId, Principal principal) {
        int userId = collectionDao.getCollectionById(collectionId).getUserId();
        String userName = userDao.getUserById(userId).getUsername();
        return userName.equals(principal.getName());
    }

}
