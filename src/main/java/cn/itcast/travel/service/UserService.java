package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/18 21:27
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    boolean registerUser(User user);

    boolean active(String activeCode);

    User login(User user);
}
