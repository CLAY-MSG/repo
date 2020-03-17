package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 16:13
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findFavoriteByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {

        }
        return favorite;
    }

    @Override
    public int findFavoriteCount(int rid) {
        int favoriteCount = 0;
        try {
            String sql = "select count(*) from tab_favorite where rid = ?";
            favoriteCount = jdbcTemplate.queryForObject(sql, Integer.class, rid);
        } catch (DataAccessException e) {

        }
        return favoriteCount;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        jdbcTemplate.update(sql,rid,new Date(),uid);

    }
}
