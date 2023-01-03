package com.techelevator.model;

import java.util.List;

public class ComicInfo {

    private int id;
    private String title;
    private int issueNumber;
    private String description;
    private String isbn;
    private String format;
    private int pageCount;
    private SeriesInfo series;
    private List<DateInfo> dates;
    private Thumbnail thumbnail;
    private Creators creators;
    private Characters characters;

    public ComicInfo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public SeriesInfo getSeries() {
        return series;
    }

    public void setSeries(SeriesInfo series) {
        this.series = series;
    }

    public List<DateInfo> getDates() {
        return dates;
    }

    public void setDates(List<DateInfo> dates) {
        this.dates = dates;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }
}
