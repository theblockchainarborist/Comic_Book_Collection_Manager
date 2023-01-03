package com.techelevator.dao;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;

@Component
public class JdbcComicDao implements ComicDao {

    private final JdbcTemplate jdbc;

    public JdbcComicDao(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean addComicToCollection(int comicIdApi, int collectionId) {
        if (searchForComicId(comicIdApi) != -1) {
            return addExistingComicToCollection(comicIdApi, collectionId);
        }
        return addNewComicToCollection(comicIdApi, collectionId);
    }

    @Override
    public boolean deleteComicFromCollection(int collectionId, int comicIdApi) {
        String sql = "DELETE FROM collection_comic_book WHERE (collections_id = ? AND comic_book_id = (SELECT id FROM comic_book WHERE api_id = ?));";
        try {
            jdbc.update(sql, collectionId, comicIdApi);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This comic was not deleted.");
        }
        return false;
    }

    public boolean addNewComicToCollection(int comicIdApi, int collectionId) {
        String createSql = "BEGIN TRANSACTION; " +
                "INSERT INTO comic_book (api_id) VALUES (?); " +
                "INSERT INTO collection_comic_book (collections_id, comic_book_id) VALUES (?, (SELECT id FROM comic_book WHERE api_id = ?)); " +
                "COMMIT;";
        try {
            jdbc.update(createSql, comicIdApi, collectionId, comicIdApi);
            return true;
        } catch (Exception e) {
            jdbc.execute("ROLLBACK;");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This comic is already in your collection.");
        }
    }

    public boolean addExistingComicToCollection(int comicIdApi, int collectionId) {
        String addComic = "INSERT INTO collection_comic_book (collections_id, comic_book_id) VALUES (?, (SELECT id FROM comic_book WHERE api_id = ?));";
        try {
            jdbc.update(addComic, collectionId, comicIdApi);
            return true;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This comic is already in your collection.");
        }
    }

    public int searchForComicId(int comicIdApi) {
        String sql = "SELECT id from comic_book WHERE api_id = ?;";
        SqlRowSet result = jdbc.queryForRowSet(sql, comicIdApi);
        if (result.next()) {
            int comicId = result.getInt("id");
            return comicId;
        }
        return -1;
    }

}
