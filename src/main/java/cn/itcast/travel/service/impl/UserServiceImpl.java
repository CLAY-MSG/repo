package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/18 21:27
 */
public class UserServiceImpl implements UserService {
    UserDao ud = new UserDaoImpl();
    @Override
    public boolean registerUser(User user) {
        User userByUsername = ud.findUserByUsername(user.getUsername());
        if (userByUsername!=null){
            return false;
        }
        user.setStatus("N");
        String uuid = UuidUtil.getUuid();
        user.setCode(uuid);
        ud.saveUser(user);
        String content = "恭喜您，注册成功，点击<a href = 'http://localhost/travel/UserServlet/active?activeCode="+user.getCode()+"'>激活</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活账号");
        return true;
    }

    @Override
    public boolean active(String activeCode) {
        User userByCode = ud.findUserByCode(activeCode);
        if (userByCode!=null){
            ud.updateUserStatus(userByCode);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public User login(User user) {
        return ud.checkUser(user.getUsername(),user.getPassword());
    }
}
