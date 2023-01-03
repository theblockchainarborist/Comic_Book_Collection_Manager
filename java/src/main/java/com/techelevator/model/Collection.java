package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Collection {

    private int id;
    private String name;
    private int userId;
    private String status;
    private List<Integer> comicIds = new ArrayList<>();

    public Collection() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getComicIds() {
        return comicIds;
    }

    public void setComicIds(List<Integer> comicIds) {
        this.comicIds = comicIds;
    }
}
