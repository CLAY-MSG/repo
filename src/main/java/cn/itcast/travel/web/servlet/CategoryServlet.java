package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author CLAY
 * @version 1.1
 * @data 2020/1/19 17:28
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService cs = new CategoryServiceImpl();
    public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = cs.findAllCategory();
        writeValue(categoryList,response);
    }

}
