package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 16:11
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findFavoriteByRidAndUid(Integer.parseInt(rid), uid);
        boolean flag = favorite != null;
        return flag;
    }

    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.addFavorite(Integer.parseInt(rid),uid);
    }
}
