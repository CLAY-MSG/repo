package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 12:58
 */
public interface RouteSellerDao {
    Seller findSeller(int sid);
}
