package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 19:34
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid, String rname) {
        /*String sql = "select  count(*) from tab_route where cid = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,cid);*/
        String sql = "select  count(*) from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        ArrayList<Object> condition = new ArrayList<>();
        if (cid!=0){
            sb.append(" and cid = ?");
            condition.add(cid);
        }
        if (rname != null && rname.length() != 0){
            sb.append(" and rname like ?");
            condition.add("%"+rname+"%");
        }
        sql = sb.toString();
        return jdbcTemplate.queryForObject(sql,Integer.class,condition.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        List<Route> routeList = null;
        try {
            /*String sql = "select * from tab_route where cid = ? limit ?,?";
            routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid, start, pageSize);*/
            String sql = "select * from tab_route where 1=1";
            StringBuilder sb = new StringBuilder(sql);
            ArrayList<Object> condition = new ArrayList<>();
            if (cid!=0){
                sb.append(" and cid = ?");
                condition.add(cid);
            }
            if (rname!=null&&rname.length()>0){
                sb.append(" and rname like ?");
                condition.add("%"+rname+"%");
            }
            sb.append(" limit ?,?");
            condition.add(start);
            condition.add(pageSize);
            sql = sb.toString();
            routeList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Route>(Route.class),condition.toArray());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return routeList;
    }
    @Override
    public Route findOneRoute(int rid) {
        Route route = null;
        try {
            String sql = "select * from tab_route where rid = ?";
            route = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid);
        } catch (DataAccessException e) {
        }
        return route;
    }
}
