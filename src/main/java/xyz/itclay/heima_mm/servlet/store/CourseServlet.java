package xyz.itclay.heima_mm.servlet.store;


import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.store.Course;
import xyz.itclay.heima_mm.service.impl.store.CourseServiceImpl;
import xyz.itclay.heima_mm.service.store.CourseService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 学科管理
 *
 * @author ZhangSenmiao
 * @date 2021/3/5 13:14
 **/
@WebServlet("/course/*")
public class CourseServlet extends BaseServlet {

    private final CourseService CourseService = new CourseServiceImpl();

    /**
     * 获取学科信息
     */
    public void getCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Course> list = CourseService.findByPage(pageNum, pageSize);
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/store/course/list.jsp").forward(req, resp);
    }

    /**
     * 添加学科信息
     */
    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = BeanUtil.fillBean(req, Course.class);
        CourseService.addCourse(course);
        resp.sendRedirect("/course/getCourse");
    }

    /**
     * 修改学科信息
     */
    public void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = BeanUtil.fillBean(req, Course.class);
        CourseService.updateCourse(course);
        resp.sendRedirect("/course/getCourse");
    }

    /**
     * 根据id删除学科信息
     */
    public void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CourseService.deleteCourse(id);
        resp.sendRedirect("/course/getCourse");
    }
}
