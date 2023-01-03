package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import javax.sql.DataSource;

@Component
public class JdbcStatsDao implements StatsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcStatsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int totalNumberOfCollections() {
        String sql = "SELECT COUNT(*) FROM collections;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()) {
                return result.getInt("count");
            }
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public int highestNumberOfComicsInCollection() {
        String sql = "SELECT MAX(count) FROM (SELECT collections_id, COUNT(*) FROM collection_comic_book GROUP BY collections_id) AS collection_count;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            if (result.next()) {
                return result.getInt("max");
            }
        } catch (ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public String[] userWithMostComics() {
        String sql = "SELECT username, MAX(count) " +
                "FROM (SELECT u.username, COUNT(ccb.comic_book_id) FROM users u " +
                    "INNER JOIN collections c ON c.user_id = u.user_id " +
                    "INNER JOIN collection_comic_book ccb ON ccb.collections_id = c.id GROUP BY username) AS comic_max " +
                "GROUP BY username ORDER BY max DESC LIMIT 3;";
        String[] output = new String[3];

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            int index = 0;
            while(results.next()){
                String username = results.getString("username");
                int collectionCount = results.getInt("max");
                output[index] = username + " : " + collectionCount;
                index ++;
            }
        } catch (ResourceAccessException rae) {
            System.out.println(rae.getMessage());
        }

        return output;
    }

    @Override
    public String[] userWithMostCollections() {
        String sql = "SELECT username, MAX(count) " +
                "FROM (select u.username, COUNT(c.id) FROM users u " +
                    "INNER JOIN collections c ON c.user_id = u.user_id GROUP BY u.username) as top_collections " +
                "GROUP BY username ORDER BY max DESC LIMIT 3;";

        String[] output = new String[3];

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            int index = 0;
            while(results.next()){
                String username = results.getString("username");
                int collectionCount = results.getInt("max");
                output[index] = username + " : " + collectionCount;
                index ++;
            }
        } catch (ResourceAccessException rae) {
            System.out.println(rae.getMessage());
        }
        return output;

    }


}
