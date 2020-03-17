package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 17:38
 */
public interface CategoryDao {
    List<Category> findAllCategory();
}
