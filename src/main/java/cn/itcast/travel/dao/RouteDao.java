package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 19:32
 */
public interface RouteDao {
    int findTotalCount(int cid, String rname);
    List<Route> findByPage(int cid, int start, int pageSize, String rname);
    Route findOneRoute(int rid);
}
