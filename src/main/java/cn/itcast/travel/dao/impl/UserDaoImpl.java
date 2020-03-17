package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/18 21:27
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username=?";
            //jdbcTemplate.queryForObject封装失败不会返回null，而是会报错，所以为了避免报错，使用try/catch捕获异常
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email,status,code) value (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
    }

    @Override
    public User findUserByCode(String activeCode) {
        User user = null;
        try {
            String sql = "select * from tab_user where code=?";
            //jdbcTemplate.queryForObject封装失败不会返回null，而是会报错，所以为了避免报错，使用try/catch捕获异常
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), activeCode);
        } catch (DataAccessException e) {

        }
        return user;
    }

    @Override
    public void updateUserStatus(User userByCode) {
        String sql = "update tab_user set status = 'Y' where uid = ?";
        jdbcTemplate.update(sql, userByCode.getUid());
    }

    @Override
    public User checkUser(String username ,String password) {
        User queryUser = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            queryUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (DataAccessException e) {
        }
        return queryUser;
    }
}
