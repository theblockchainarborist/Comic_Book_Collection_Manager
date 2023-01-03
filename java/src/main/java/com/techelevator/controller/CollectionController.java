package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Collection;
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
public class CollectionController {

    private final CollectionDao collectionDao;
    private final UserDao userDao;
    private final RestMarvelService api;

    public CollectionController(CollectionDao collectionDao, UserDao userDao) {
        this.collectionDao = collectionDao;
        this.userDao = userDao;
        this.api = new RestMarvelService();
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/collections/{id}", method = RequestMethod.GET)
    public List<ComicInfo> getCollectionById(@PathVariable int id, Principal principal) {
        Collection currentCollection = collectionDao.getCollectionById(id);
        if (currentCollection == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No collection found with that ID.");
        }

        List<Integer> comicIdList = currentCollection.getComicIds();
        List<ComicInfo> comicsList = new ArrayList<>();

        if (checkCollectionPermissions(currentCollection.getId(), principal)) {
            for (Integer comicId : comicIdList) {
                ComicInfo currentComic = api.getComicById(comicId).getData().getResults().get(0);
                comicsList.add(currentComic);
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have permission to view this collection.");
        }
        return comicsList;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/shared/collections/{id}", method = RequestMethod.GET)
    public List<ComicInfo> getSharedCollectionLink(@PathVariable int id) {
        Collection currentCollection = collectionDao.getCollectionById(id);

        if (currentCollection == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No collection found with that ID.");
        }

        List<Integer> comicIdList = currentCollection.getComicIds();
        List<ComicInfo> comicsList = new ArrayList<>();

        if (comicIdList.size() > 0) {
            for (Integer comicId : comicIdList) {
                ComicInfo currentComic = api.getComicById(comicId).getData().getResults().get(0);
                comicsList.add(currentComic);
            }
        }
        return comicsList;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/collections", method = RequestMethod.GET)
    public List<Collection> getPublicCollections() {
        List<Collection> results = collectionDao.getPublicCollections();
        if (results.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to access public collections.");
        }
        return results;
    }

    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public List<Collection> getUserCollections(@PathVariable String username, Principal principal) {
        List<Collection> result = null;
        int userId = userDao.findIdByUsername(username);
        if (verifyUserId(userId, principal)) {
            result = collectionDao.getUserCollections(userId);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You may only view your own user collections.");
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/{username}", method = RequestMethod.POST)
    public boolean createCollection(@PathVariable String username, @RequestBody Collection collection, Principal principal) {
        int userId = userDao.findIdByUsername(username);
        if (verifyUserId(userId, principal)) {
            collection.setUserId(userId);
            return collectionDao.createCollection(collection);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You may only view your own user collections.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/users/{username}", method = RequestMethod.DELETE)
    public boolean deleteCollection(@PathVariable String username, @RequestBody Collection collection, Principal principal) {
        int userId = userDao.findIdByUsername(username);
        if (verifyUserId(userId, principal)) {
            return collectionDao.deleteCollection(collection.getId());
        }
        return false;
    }

    public boolean verifyUserId(int id, Principal principal) {
        String userName = userDao.getUserById(id).getUsername();
        return userName.equals(principal.getName());
    }

    public boolean checkCollectionPermissions(int collectionId, Principal principal) {
        Collection collection = collectionDao.getCollectionById(collectionId);
        int userId = collection.getUserId();
        if (collection.getStatus().equals("public")) {
            return true;
        }
        // Checks permissions for private collections
        try {
            return verifyUserId(userId, principal);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have permission to view this collection.");
        }
    }
}
