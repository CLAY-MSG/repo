package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 17:02
 */
@WebServlet("/UserServlet/*")
public class UserServlet extends BaseServlet {
    private UserService us =new UserServiceImpl();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check = request.getParameter("check");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        if (checkcode_server!=null && !checkcode_server.equalsIgnoreCase(check)) {
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误!");
            ObjectMapper om = new ObjectMapper();
            String resultInfoJson = om.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(resultInfoJson);
        }else {
            //验证码只能被有效使用一次
            request.getSession().removeAttribute("CHECKCODE_SERVER");
            //1.获取数据
            Map<String, String[]> parameterMap = request.getParameterMap();
            //2.封装User对象
            User user = new User();
            try {
                BeanUtils.populate(user, parameterMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            //3.调用Service完成注册
            boolean flag = us.registerUser(user);
            //4.根据Service的返回，提示信息
            ResultInfo resultInfo = new ResultInfo();
            if (flag) {
                resultInfo.setFlag(true);

            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("注册失败!");
            }
            ObjectMapper om = new ObjectMapper();
            String resultInfoJson = om.writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(resultInfoJson);
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        User loginUser = us.login(user);
        ResultInfo resultInfo = new ResultInfo();
        if (loginUser==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名密码或错误");
        }
        if (loginUser!=null && !"Y".equals(loginUser.getStatus())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未激活，请激活");
        }
        if (loginUser!=null && "Y".equals(loginUser.getStatus())){
            request.getSession().setAttribute("user",loginUser);
            resultInfo.setFlag(true);
        }
        ObjectMapper om = new ObjectMapper();
        String loginJson = om.writeValueAsString(resultInfo);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(loginJson);
    }
    public void findOneUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        om.writeValue(response.getOutputStream(),user);
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/index.html");
    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        String msg = null;
        if (activeCode!=null){
            UserService us = new UserServiceImpl();
            boolean flag = us.active(activeCode);
            if (flag){
                msg = "激活成功,请<a href = 'login.html'>登录</a>";
            }else {
                msg = "激活失败";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }





}
