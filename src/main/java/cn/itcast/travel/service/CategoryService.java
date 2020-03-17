package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 17:37
 */
public interface CategoryService {
    List<Category> findAllCategory();
}
