package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/18 21:27
 */
public interface UserDao {
    User findUserByUsername(String username);
    void saveUser(User user);

    User findUserByCode(String activeCode);

    void updateUserStatus(User userByCode);

    User checkUser(String username ,String password);
}
