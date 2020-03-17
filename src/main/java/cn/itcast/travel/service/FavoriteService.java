package cn.itcast.travel.service;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 16:10
 */
public interface FavoriteService {
    boolean isFavorite(String rid,int uid);

    void addFavorite(String rid, int uid);
}
