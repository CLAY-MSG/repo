package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 12:59
 */
public class RouteSellerDaoImpl implements RouteSellerDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findSeller(int sid) {
        Seller seller = null;
        try {
            String sql = "select * from tab_seller where  sid = ?";
            seller = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
        } catch (DataAccessException e) {

        }
        return seller;
    }
}
