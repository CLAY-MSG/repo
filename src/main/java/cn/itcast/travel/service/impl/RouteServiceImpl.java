package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.RouteSellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 19:32
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao rd = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private RouteSellerDao routeSellerDao = new RouteSellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();
        int totalCount = rd.findTotalCount(cid,rname);
        routePageBean.setTotalCount(totalCount);
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        routePageBean.setTotalPage(totalPage);
        int start = (currentPage-1)*pageSize;
        List<Route> pageContent = rd.findByPage(cid,start,pageSize,rname);

        System.out.println(pageContent);

        routePageBean.setPageContent(pageContent);
        return routePageBean;
    }

    @Override
    public Route findRouteDetail(int rid) {
        Route route = rd.findOneRoute(rid);
        List<RouteImg> routeImg = routeImgDao.findRouteImg(rid);
        route.setRouteImgList(routeImg);
        Seller seller = routeSellerDao.findSeller(route.getSid());
        route.setSeller(seller);
        int favoriteCount = favoriteDao.findFavoriteCount(rid);
        route.setCount(favoriteCount);
        return route;
    }
}
