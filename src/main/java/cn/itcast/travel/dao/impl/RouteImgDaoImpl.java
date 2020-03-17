package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/20 12:56
 */
public class RouteImgDaoImpl implements RouteImgDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findRouteImg(int rid) {
        List<RouteImg> routeImgList = null;
        try {
            String sql = "select * from tab_route_img where rid = ?";
            routeImgList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
        } catch (DataAccessException e) {

        }
        return routeImgList;
    }
}
