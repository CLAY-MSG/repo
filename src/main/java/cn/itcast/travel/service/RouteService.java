package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 19:29
 */
public interface RouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
    Route findRouteDetail(int rid);
}
