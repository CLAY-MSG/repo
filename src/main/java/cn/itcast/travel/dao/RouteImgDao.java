package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 12:55
 */
public interface RouteImgDao {
    List<RouteImg> findRouteImg(int rid);
}
