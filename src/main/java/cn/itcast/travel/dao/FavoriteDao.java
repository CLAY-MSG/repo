package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 16:12
 */
public interface FavoriteDao {
    Favorite findFavoriteByRidAndUid(int rid,int uid);

    int findFavoriteCount(int rid);
    void addFavorite(int rid, int uid);

}
