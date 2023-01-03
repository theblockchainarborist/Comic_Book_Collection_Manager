package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao {

    private JdbcTemplate jdbc;
    private final String BASE_SQL = "SELECT id, name, user_id, status FROM collections ";
    private final String JOIN_SQL = "SELECT comic_book.api_id, collections.id, collections.name, collections.user_id, collections.status " +
                                    "FROM collections JOIN collection_comic_book " +
                                    "ON collection_comic_book.collections_id = collections.id " +
                                    "JOIN comic_book " +
                                    "ON comic_book.id = collection_comic_book.comic_book_id ";

    public JdbcCollectionDao(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection getCollectionById(int id) {
        Collection collection = null;
        String sql = BASE_SQL + "WHERE id = ?";

        try {
            SqlRowSet rowSet = jdbc.queryForRowSet(sql, id);
            if(rowSet.next()) {
                collection = mapCollectionToRowSet(rowSet);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return collection;
    }

    @Override
    public List<Collection> getPublicCollections() {
        List<Collection> allCollections = new ArrayList<>();
        String sql = BASE_SQL + "WHERE status = ?";
        String status = "public";

        try {
            SqlRowSet rowSet = jdbc.queryForRowSet(sql, status);
            while(rowSet.next()) {
                allCollections.add(mapCollectionToRowSet(rowSet));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allCollections;
    }

    @Override
    public List<Collection> getUserCollections(int userId) {
        List<Collection> allCollections = new ArrayList<>();
        String sql = BASE_SQL + "WHERE user_id = ?";

        try {
            SqlRowSet rowSet = jdbc.queryForRowSet(sql, userId);
            while(rowSet.next()) {
                allCollections.add(mapCollectionToRowSet(rowSet));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return allCollections;
    }

    @Override
    public List<Integer> getComicsByCollectionId(int collectionId) {
        List<Integer> collectionComicIds = new ArrayList<>();
        String sql = JOIN_SQL + "WHERE collections.id = ?";

        try {
            SqlRowSet rowSet = jdbc.queryForRowSet(sql, collectionId);
            while(rowSet.next()) {
                int comicId = rowSet.getInt("api_id");
                collectionComicIds.add(comicId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return collectionComicIds;
    }

    @Override
    public boolean createCollection(Collection collection) {
        String sql = "INSERT INTO collections (name, user_id, status) " +
                "VALUES (?, ?, ?)";

        try {
            return jdbc.update(sql, collection.getName(), collection.getUserId(), collection.getStatus()) == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteCollection(int collectionId) {
        String sql = "DELETE FROM collections WHERE id = ?;";
        try {
            return jdbc.update(sql, collectionId) == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Collection mapCollectionToRowSet(SqlRowSet rowSet) {
        Collection collection = new Collection();
        collection.setId(rowSet.getInt("id"));
        collection.setName(rowSet.getString("name"));
        collection.setStatus(rowSet.getString("status"));
        collection.setUserId(rowSet.getInt("user_id"));
        collection.setComicIds(getComicsByCollectionId(rowSet.getInt("id")));
        return collection;
    }
}
